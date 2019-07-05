$root_path = split-path -parent $MyInvocation.MyCommand.Definition
cd $root_path
Write-Host "ROOT_PATH: $pwd"

vagrant provision --provision-with oraclexe_clean
