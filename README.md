# music-CRUD

一个简单的基于SpringBoot 2.6.2 、Maven 3 和 MyBatis 的CRUD项目。

仅后端，利用 Postman 和 MySQL 完成接口测试。

- 错误码和返回结果体统一定义
- MyBatis 逆向工程生成Mapper
- jackson 自动格式化参数日期字段

## 数据库设计

#### 专辑表 ablum

|  字段名   |     类型     |    描述     | 是否可空 |
| :-------: | :----------: | :---------: | :------: |
|    id     |     int      | 从1开始自增 |    否    |
|   name    | varchar(255) |  专辑名称   |    否    |
| artist_id |     int      |   艺人ID    |    否    |
|   genre   | varchar(255) |  音乐流派   |    是    |
|   year    |     int      |  专辑年代   |    是    |
| publisher | varchar(255) |   发行商    |    是    |

#### 歌手表 artist

|  字段名  |     类型     |    描述     | 是否可空 |
| :------: | :----------: | :---------: | :------: |
|    id    |     int      | 从1开始自增 |    否    |
|   name   | varchar(255) |  艺人名称   |    否    |
|  region  | varchar(255) |  国家/地区  |    是    |
| birthday |  TIMESTAMP   |  出生日期   |    是    |

#### 用户表 user

|   字段名    |     类型     |    描述     | 是否可空 |
| :---------: | :----------: | :---------: | :------: |
|     id      |     int      | 从1开始自增 |    否    |
|  username   | varchar(255) |   用户名    |    否    |
|  password   | varchar(255) |    密码     |    否    |
| create_time |  TIMESTAMP   |             |    是    |
| update_time |  TIMESTAMP   |             |    是    |

