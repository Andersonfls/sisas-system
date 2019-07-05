#!/usr/bin/python
import jaydebeapi
import sys
import time

con = None
semconexao = True
MAX_TENTATIVA = 10
tentativa = 1

while (tentativa <= MAX_TENTATIVA and semconexao):
  print "Tentando criar objetos de banco de dados... ", tentativa, " de ", MAX_TENTATIVA
  sys.stdout.flush()
  try:
    con = jaydebeapi.connect('oracle.jdbc.driver.OracleDriver',
                              'jdbc:oracle:thin:@localhost:1521:xe', [ 'system', 'oracle'],
                              r'/vagrant/scripts/ojdbc7-12.1.0.jar')
    semconexao = False
  except Exception, e:
    print e
    time.sleep(30)
  tentativa = tentativa + 1
  sys.stdout.flush()

if semconexao:
  print "Nao foi possivel os objetos de banco de dados banco de dados."
  sys.exit(1)

curs = con.cursor()
curs.execute("CREATE USER PTOLOMEU IDENTIFIED BY PTOLOMEU")
curs.execute("grant connect to PTOLOMEU")
curs.execute("grant all privileges to PTOLOMEU")
curs.close()
con.close()

print "Objetos de BD foram criados com sucesso!"
