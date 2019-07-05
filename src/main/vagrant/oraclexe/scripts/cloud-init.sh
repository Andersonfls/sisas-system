#!/bin/sh
export DEBIAN_FRONTEND=noninteractive;

# Habilitando repositorios oficiais do Puppet 5
wget https://apt.puppetlabs.com/puppet5-release-trusty.deb
sudo dpkg -i puppet5-release-trusty.deb
sudo add-apt-repository ppa:openjdk-r/ppa -y
sudo apt-get update
sudo apt-get -y install openjdk-8-jdk
sudo apt-get -y install python-pip
sudo apt-get -y install python-dev
sudo pip install JayDeBeApi


# Instalando o puppet agent (Puppet 5)
sudo apt-get install -y puppet-agent

# Adicionando um Link simbolico para o puppet ficar no PATH
sudo ln -sf /opt/puppetlabs/bin/puppet /usr/bin/puppet

# Adicionando modulos para o puppet
sudo puppet module install puppetlabs-docker
sudo puppet module install puppetlabs-ntp
sudo puppet module install puppetlabs-stdlib
sudo puppet module install puppetlabs-concat
sudo puppet module install puppetlabs-apt
sudo puppet module install puppetlabs-vcsrepo
sudo puppet module install saz-timezone
sudo puppet module install thejandroman-openvpn_client --version 0.1.4
sudo puppet module install abrader-gms --version 1.0.3
sudo puppet module install puppetlabs-java --version 2.3.0
sudo puppet module install saz-timezone --version 5.0.2

sudo -i
cp -a /vagrant/puppet/hiera.yaml /opt/puppetlabs/puppet/hiera.yaml

puppet apply /vagrant/puppet/manifests/default.pp

python /vagrant/scripts/checa_conexao.sh
python /vagrant/scripts/create-default-db-objects.py
