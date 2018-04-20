
## Win7下快速配置Docker环境
*[Docker ToolBox](https://get.daocloud.io/toolbox/)*

## 国内docker镜像加速
`sudo sed -i "s|EXTRA_ARGS='|EXTRA_ARGS='--registry-mirror=http://d9045f1e.m.daocloud.io |g" /var/lib/boot2docker/profile`

## Docker UI管理实例启动
`docker run -d -p 19000:9000 --restart always -v /var/run/docker.sock:/var/run/docker.sock -v /opt/portainer:/data portainer/portainer`