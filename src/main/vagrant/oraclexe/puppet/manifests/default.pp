node 'oraclexe' {

  class { 'docker':
    version => '17.09.0~ce-0~ubuntu',
  }

  class { 'timezone':
    timezone => 'America/Sao_Paulo',
  }

  class { 'docker::compose':
    ensure  => present,
    version => '1.15.0',
  }

  docker_compose { '/vagrant/docker-compose.yml':
    ensure  => present,
  }
}


