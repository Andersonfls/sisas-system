#!/usr/bin/env bash
sudo -i
cd /vagrant
docker-compose down
docker-compose up -d
python /vagrant/scripts/wait-oracle.py
python /vagrant/scripts/create-default-db-objects.py

