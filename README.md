API v0.1

# Specifications

This describes the resources that make up the official Rebase API by [drakeet](https://github.com/drakeet). If you have any problems or requests please contact [drakeet](https://github.com/drakeet) or create an issue.

## Schema

All API access is over HTTPS, and accessed from the `https://api.drakeet.com/rebase`. All data is sent and received as JSON.

```
Content-Type: application/json; charset=utf-8
```

All timestamps are returned in ISO 8601 format:

```
YYYY-MM-DDTHH:MM:SSZ
```

## Parameters

Many API methods take optional parameters. For GET requests, any parameters not specified as a segment in the path can be passed as an HTTP query string parameter:

```
GET https://api.drakeet.com/rebase/categories/drakeet/fun/feeds?size=15&last_id=123
```

For POST, PATCH, PUT, and DELETE requests, parameters not included in the URL should be encoded as JSON with a Content-Type of `application/json`.

## Access Token

Access token is required for all  POST, PATCH, PUT, and DELETE requests.

Header

```
Authorization: token YOUR-TOKEN
```

## Client Errors

There is only one type of errors on API calls.

```
Status 4xx Description

{
  "error": "Not Found"
}
```

# Users

By default, all the UGCs are public, but only the owner can publish or change his categories and feeds.

## Register

```
POST /users
```

Input

| Name      |    Type | Description  |
| :-------- | :--------| :-- |
| username | String | **Required**. The username of the user. |
| password | String | **Required**. The password of the user. |
| name | String | **Required**. The name of the user. |
| email | String | **Required**. The email of the user to contact. |

Response

```
Status: 201 Created

{
    "username": "drakeet",
    "name": "drakeet",
    "email": "drakeet.me@gmail.com"
}
```

## Authentications

```
GET /authentications/:username
```
Parameters

| Name      |    Type | Description  |
| :-------- | :--------| :-- |
| password | String | The password of the user. |

Response

```
Status: 200 OK

{
  "access_token": "e72e16c7e42f292c6912e7710c838347ae178b4a"
}
```

# Categories

A user can create up to 11 categories.

## List categories of someone

```
GET /categories/:owner
```

Response

```
Status: 200 OK

[
    {
        "key": "a key",
        "name": "a name of the category",
        "rank": 1,
        "owner": "drakeet"
    },
    {
        "key": "a key",
        "name": "a name of the category",
        "rank": 2,
        "owner": "drakeet"
    }
]
```

## Create a category

```
POST /categories/:owner
```

Parameters

| Name      |    Type | Description  |
| :-------- | :--------| :-- |
| key | String | **Required**. The key of the category. It's the id of the category. |
| name | String | **Required**. The name of the category. |
| rank | int | **Required**. The rank of the category. |

Response

```
Status: 201 Created

{
    "key": "a key",
    "name": "a name of the category",
    "rank": 1,
    "owner": "drakeet"
}
```

# Feeds

A feed may contain anything relating to its category.

## List feeds in a category

```
GET /categories/:owner/:category/feeds
```

Parameters

| Name      |    Type | Description  |
| :-------- | :--------| :-- |
| last_id | String | Default: null. The last feed id of the feeds. |
| size | int | Default: 15. The size of the feeds. |

Response

```
Status: 200 OK

[
    {
        "_id": "1",
        "title": "a title",
        "content": "a content",
        "url": "an url",
        "category": "a category",
        "owner": "an owner",
        "cover": "a cover url",
        "published_at": "2017-01-19T05:44:38Z"
    },
    {
        "_id": "2",
        "title": "a title",
        "content": "a content",
        "url": "an url",
        "category": "a category",
        "owner": "an owner",
        "cover": "a cover url",
        "published_at": "2017-01-19T05:44:38Z"
    }
]
```

## Create a feed

```
POST /categories/:owner/:category/feeds
```

Input

| Name      |    Type | Description  |
| :-------- | :--------| :-- |
| category | String | **Required**. The category of the feed. |
| title | String | The title of the feed. |
| content | String | The content of the feed. |
| url | String | The target URL of the feed. |
| cover | String | The cover URL of the feed. |

Response

```
Status: 201 Created

{
    "_id": "1",
    "title": "a title",
    "content": "a content",
    "url": "an url",
    "category": "a category",
    "owner": "an owner",
    "cover": "a cover url",
    "published_at": "2017-01-19T05:44:38Z"
}
```
