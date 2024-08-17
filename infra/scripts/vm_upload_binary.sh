#!/bin/bash
scp -r /home/daniel/workspace/MTASet/out/production/MTASet adminuser@${VM_IP}:/home/adminuser/mtaset
scp -r test_script.sh adminuser@${VM_IP}:/home/adminuser/occab