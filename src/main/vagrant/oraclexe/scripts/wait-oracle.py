#!/usr/bin/python
import jaydebeapi
import time
import sys


con = None
semconexao = True
MAX_TENTATIVA = 20
tentativa = 1

while (tentativa <= MAX_TENTATIVA and semconexao):
  print "Tentando conectar ao Oracle... ", tentativa, " de ", MAX_TENTATIVA
  sys.stdout.flush()
  try:
    con = jaydebeapi.connect('oracle.jdbc.driver.OracleDriver',
                              'jdbc:oracle:thin:@localhost:1521:xe', [ 'system', 'oracle'],
                              r'/vagrant/scripts/ojdbc7-12.1.0.jar')
    con.close()
    print "Conexao estabelecida com sucesso!"
    semconexao = False
  except Exception, e:
    print e
    time.sleep(20)
  tentativa = tentativa + 1
  sys.stdout.flush()

if semconexao:
  print "Nao foi possivel conectar ao banco de dados."
  sys.exit(1)
