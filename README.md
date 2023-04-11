# eazy-music

音乐后台服务

## process
前端发送Dto格式数据给controller-》controller传递dto给service -》service转换为实体传递给数据库
数据返回entity给service -》service转换为dto给controller -》 controller转换为vo返回给前端

## Step 4

1. 完成用户业务接口。
2. 引入validation-starter做用户信息校验。
3. 设计dto，添加更新用户自动映射。 UserMapper.updateEntity

## Day31 2023-03-08

1. 完成音乐管理服务api
   1. create，update，search，publish，close

## Day 34 2023-03-11
1. 实现文件上传接口
   1. FileEntity，FileDto，FileUploadRequestDto相互转换
   2. FileUploadDto, FileUploadVo相互转换
   3. 前端发送fileUploadDto格式数据给fileController-》controller传递dto给fileService -》 
      1. fileService转换为实体类存储到数据库，同时验证将真实文件存储到对象存储，获取验证信息response封装成dto -》vo返回给前端
      2. 前端通过验证信息
