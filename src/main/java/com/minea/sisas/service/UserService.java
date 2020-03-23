package com.minea.sisas.service;

import com.minea.sisas.domain.Authority;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.User;
import com.minea.sisas.domain.enumeration.TipoAcao;
import com.minea.sisas.repository.AuthorityRepository;
import com.minea.sisas.config.Constants;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.security.AuthoritiesConstants;
import com.minea.sisas.security.SecurityUtils;
import com.minea.sisas.service.dto.SegurancaLogDTO;
import com.minea.sisas.service.dto.SistemaAguaLogDTO;
import com.minea.sisas.service.util.RandomUtil;
import com.minea.sisas.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final CacheManager cacheManager;

    @Autowired
    private final SegurancaLogService segurancaLogService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository,
                       CacheManager cacheManager, SegurancaLogService segurancaLogService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.cacheManager = cacheManager;
        this.segurancaLogService = segurancaLogService;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
           .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
                return user;
           });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
                return user;
            });
    }

    public User registerUser(UserDTO userDTO, String password) {

        User newUser = new User();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setEnderecoCep((userDTO.getEnderecoCep()));
        newUser.setEnderecoLote(userDTO.getEnderecoLote());
        newUser.setNome(userDTO.getNome());
        newUser.setEnderecoCep(userDTO.getEnderecoLogadouro());
        newUser.setEnderecoComplemento(userDTO.getEnderecoComplemento());
        newUser.setEnderocoBairro(userDTO.getEnderecoBairro());
        newUser.setEnderecoCidade(userDTO.getEnderecoCidade());
        newUser.setEnderecoUf(userDTO.getEnderecoUf());
        newUser.setRfAssociado(userDTO.getRfAssociado());
        newUser.setBrAssociado(userDTO.getBrAssociado());
        newUser.setQrAssociado(userDTO.getQrAssociado());
        newUser.setNfAssociado(userDTO.getNfAssociado());
        newUser.setPerfilAdm(userDTO.getPerfilAdm());
        newUser.setModConfig(userDTO.getModConfig());
        newUser.setModBalcao(userDTO.getModBalcao());
        newUser.setModToten(userDTO.getModToten());
        newUser.setModMobile(userDTO.getModMobile());
        newUser.setCelular(userDTO.getCelular());
        newUser.setTelefone(userDTO.getTelefone());
        newUser.setEmail(userDTO.getEmail());
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setLangKey(userDTO.getLangKey());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        newUser.setProvincia(userDTO.getProvincia());
        newUser.setMunicipio(userDTO.getMunicipio());
        newUser.setComuna(userDTO.getComuna());
        userRepository.save(newUser);
        if (Objects.nonNull(userDTO.getId())) {
            logSave(TipoAcao.ATUALIZACAO, newUser);
        } else {
            logSave(TipoAcao.INCLUSAO, newUser);
        }
        cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(newUser.getLogin());
        cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(newUser.getEmail());
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setNome(userDTO.getNome());
        user.setEnderecoCep(userDTO.getEnderecoCep());
        user.setEnderecoCep(userDTO.getEnderecoLogadouro());
        user.setEnderecoLote(userDTO.getEnderecoLote());
        user.setEnderecoComplemento(userDTO.getEnderecoComplemento());
        user.setEnderocoBairro(userDTO.getEnderecoBairro());
        user.setEnderecoCidade(userDTO.getEnderecoCidade());
        user.setEnderecoUf(userDTO.getEnderecoUf());
        user.setRfAssociado(userDTO.getRfAssociado());
        user.setBrAssociado(userDTO.getBrAssociado());
        user.setQrAssociado(userDTO.getQrAssociado());
        user.setNfAssociado(userDTO.getNfAssociado());
        user.setPerfilAdm(userDTO.getPerfilAdm());
        user.setModConfig(userDTO.getModConfig());
        user.setModBalcao(userDTO.getModBalcao());
        user.setModToten(userDTO.getModToten());
        user.setModMobile(userDTO.getModMobile());
        user.setTelefone(userDTO.getTelefone());
        user.setCelular(userDTO.getCelular());
        user.setEmail(userDTO.getEmail());
        user.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                .map(authorityRepository::findOne)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        user.setProvincia(userDTO.getProvincia());
        user.setMunicipio(userDTO.getMunicipio());
        user.setComuna(userDTO.getComuna());
        userRepository.save(user);
        logSave(TipoAcao.INCLUSAO, user);
        cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
        cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (nome, email, language) for the current user.
     * @param nome name of user
     * @param enderecoCep cep
     * @param enderecoLogadouro  logadouro
     * @param enderecoLote lote
     * @param enderecoComplemento complemento
     * @param enderecoBairro bairro
     * @param enderecoCidade cidade
     * @param rfAssociado rf_associado
     * @param brAssociado br_associado
     * @param qrAssociado qr_associado
     * @param nfAssociado nf_associado
     * @param perfilAdm pefil de adm
     * @param modConfig modo perfil
     * @param modBalcao modo balcão
     * @param modToten modo toten
     * @param modMobile modo mobile
     * @param celular telefone usuario
     * @param telefone usuario telefone
     * @param enderecoUf uf
     * @param email email id of user
     * @param langKey language key
     * @param imageUrl image URL of user
     */
    public void updateUser(String nome, String enderecoCep, String enderecoLogadouro, Integer enderecoLote,
                           String enderecoComplemento, String enderecoBairro, String enderecoCidade, String enderecoUf,
                           String rfAssociado, String brAssociado, String qrAssociado, String nfAssociado, Boolean perfilAdm,
                           Boolean modConfig, Boolean modBalcao, Boolean modToten, Boolean modMobile, String celular, String telefone,
                           String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                user.setNome(nome);
                user.setEnderecoCep(enderecoCep);
                user.setEnderecoLogadouro(enderecoLogadouro);
                user.setEnderecoLote(enderecoLote);
                user.setEnderecoComplemento(enderecoComplemento);
                user.setEnderocoBairro(enderecoBairro);
                user.setEnderecoCidade(enderecoCidade);
                user.setEnderecoUf(enderecoUf);
                user.setRfAssociado(rfAssociado);
                user.setBrAssociado(brAssociado);
                user.setQrAssociado(qrAssociado);
                user.setNfAssociado(nfAssociado);
                user.setModConfig(modConfig);
                user.setPerfilAdm(perfilAdm);
                user.setModBalcao(modBalcao);
                user.setModToten(modToten);
                user.setModMobile(modMobile);
                user.setCelular(celular);
                user.setTelefone(telefone);
                user.setEmail(email);
                user.setLangKey(langKey);
                user.setImageUrl(imageUrl);
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        logSave(TipoAcao.ATUALIZACAO, userRepository.findOne(userDTO.getId()));
        return Optional.of(userRepository
            .findOne(userDTO.getId()))
            .map(user -> {
                user.setLogin(userDTO.getLogin());
                user.setNome(userDTO.getNome());
                user.setEnderecoCep(userDTO.getEnderecoCep());
                user.setEnderecoLogadouro(userDTO.getEnderecoLogadouro());
                user.setEnderecoLote(userDTO.getEnderecoLote());
                user.setEnderecoComplemento(userDTO.getEnderecoComplemento());
                user.setEnderocoBairro(userDTO.getEnderecoBairro());
                user.setEnderecoCidade(userDTO.getEnderecoCidade());
                user.setEnderecoUf(userDTO.getEnderecoUf());
                user.setRfAssociado(userDTO.getRfAssociado());
                user.setBrAssociado(userDTO.getBrAssociado());
                user.setQrAssociado(userDTO.getQrAssociado());
                user.setNfAssociado(userDTO.getNfAssociado());
                user.setPerfilAdm(userDTO.getPerfilAdm());
                user.setModConfig(userDTO.getModConfig());
                user.setModBalcao(userDTO.getModBalcao());
                user.setModToten(userDTO.getModToten());
                user.setModToten(userDTO.getModToten());
                user.setModMobile(userDTO.getModMobile());
                user.setCelular(userDTO.getCelular());
                user.setTelefone(userDTO.getTelefone());
                user.setEmail(userDTO.getEmail());
                user.setImageUrl(userDTO.getImageUrl());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());
                user.setProvincia(userDTO.getProvincia());
                user.setMunicipio(userDTO.getMunicipio());
                user.setComuna(userDTO.getComuna());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findOne)
                    .forEach(managedAuthorities::add);
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            logSave(TipoAcao.REMOCAO, user);
            cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
            cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String password) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                String encryptedPassword = passwordEncoder.encode(password);
                user.setPassword(encryptedPassword);
                cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
                cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
                log.debug("Changed password for User: {}", user);
            });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
            cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
            cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
        }
    }

    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    private void logSave(TipoAcao acao, User user) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        SegurancaLogDTO dto = new SegurancaLogDTO();
        dto.setAcao(acao.getDescricao());
        dto.setDtLog(LocalDate.now());
        dto.setIdUsuarioAlterado(user.getId());
        if (Objects.nonNull(username)) {
            dto.setIdUsuario(this.userRepository.buscarUserIdByUsername(username));
        }
        dto.setLog(acao.getLog());

        this.segurancaLogService.save(dto);
    }

}
