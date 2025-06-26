# 核心接口清单与字段定义
## 1. 接口清单
### 1.1 用户相关接口
```text
- `POST /api/user/register`: 用户注册
- `POST /api/user/login`: 用户登录
- `GET /api/user/me`: 获取用户信息
- `PUT /api/user/update`: 更新用户信息
- `GET /api/user/captcha`: 生成验证码
```
### 1.2 产品相关接口
```text
- `GET /api/product/list`: 获取产品列表
- `GET /api/product/detail`: 获取单个产品详情
```
### 1.3 管理员相关接口
#### 1.3.1 管理员
```text
- `POST /api/admin/login`: 管理员登录
- `GET /api/admin/info`: 获取管理员信息
- `GET /api/admin/user/list`: 获取用户列表
```
#### 1.3.2 产品管理
```text
- `POST /api/admin/product/create`: 添加新产品
- `PUT /api/admin/product/{id}/update`: 更新产品信息
- `PATCH /api/admin/product/{id}/status`: 产品状态更新
- `GET /api/admin/product/{id}`: 获取产品列表
```