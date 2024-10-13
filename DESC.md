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

# 运行后端项目

## 安装Apifox

[下载地址](https://apifox.com/?utm_source=baidu_pinzhuan&utm_medium=sem&utm_campaign=pinzhuan&utm_content=pinzhuan&utm_term=apifox)
[apt清华源](https://mirrors.tuna.tsinghua.edu.cn/help/ubuntu/)

## 安装Mysql

### 麒麟
[参考](https://blog.csdn.net/LogosTR_/article/details/125602116)

```bash
sudo apt update
sudo apt install mysql-server
mysql --version
service mysql status

# 配置
sudo mysql
use mysql;
select user, host, plugin from user;
# 将root对应的plugin由 auth_socket 改为 mysql_native_password 即使是mysql8.0也是，否则影响后续远程连接:
#MySQL8.0必须先执行此步骤设置密码，MySQL5.7可以选择先安装下面的secure！！！
alter user 'root'@'localhost' identified with mysql_native_password by 'root';

flush privileges;

exit;

```
### MAC

### DataGrip

[下载地址](https://www.jetbrains.com/datagrip/download/other.html)

## 安装Redis

[参考](https://redis.com.cn/linux-install-redis.html)

[官网](https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/install-redis-from-source/)
```bash
wget http://download.redis.io/redis-stable.tar.gz
tar -xzvf redis-stable.tar.gz
cd redis-stable
make
make BUILD_TLS=yes
make install


# 运行
redis-server &
redis-server /etc/redis.conf
```

# 运行前端项目

## 安装node

[参考](https://www.iocoder.cn/NodeJS/mac-install/)

> coke


### nvm

```bash
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.8/install.sh | bash
# or
wget -qO- https://raw.githubusercontent.com/creationix/nvm/v0.33.8/install.sh | bash

# 验证
nvm --version
```

## node

```bash
# 先安装python 3.12

nvm install stable
```

## 启动项目

```bash
git clone https://github.com/yudaocode/yudao-ui-admin-vue3.git

# 安装 pnpm，提升依赖的安装速度
npm config set registry https://registry.npmmirror.com
npm install -g pnpm
# 安装依赖
pnpm install

# 启动服务
npm run dev

```