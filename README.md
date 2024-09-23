cd /path/to/your-fork  
git remote add upstream https://github.com/original-owner/original-repository.git //only the fitst time  
git fetch upstream  
git checkout master  
git merge upstream/master  
git push origin master  
