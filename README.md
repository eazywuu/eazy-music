# eazy-music

音乐后台服务

## process
前端发送Dto格式数据给controller-》controller传递给service -》service转换为实体传递给数据库
数据返回实体给service -》service转换为dto给controller -》 controller转换为vo返回给前端

## Step 4

1. 完成用户业务接口。
2. 引入validation-starter做用户信息校验。
3. 设计dto，添加更新用户自动映射。 UserMapper.updateEntity
