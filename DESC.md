# 开发说明
- dev 是本地开发分支
- master-jdk17 是本地同步分支；始终与源仓库分支一致
- dev 合并源仓库更新，通过，在本地master-jdk17 分支上 rebase

```bash
# 1.fork开源项目到自己的仓库,比如从
github.com/abc/cherry => github.com/dhl/cherry
# 然后clone 
git clone https://github.com/dhl/cherry
# 2.接下来cd到自己的仓库，开始如下操作
cd cherry
git checkout -b dev    #默认是master，master里永远跟开源的保持一致
git pull
git checkout master   #切回master，做更新
git remote add upstream https://github.com/abc/cherry.git  #设置upstream开源仓库.
git fetch upstream master #拉取开源的仓库upstream的master到本地
git merge upstream/master  #合并到本地master
git checkout dev  #切换到dev
git rebase master  #把刚刚拉取的更新merge到dev分支

```