```json
PUT /blog
{
  "mappings": {
    "properties": {
      "id": {
        "type": "integer"
      },
      "title": {
        "type": "text",
        "analyzer": "ik_max_word"
      },
      "userId": {
        "type": "integer"
      },
      "typeId": {
        "type": "integer"
      },
      "description": {
        "type": "text",
        "analyzer": "ik_max_word"
      },
      "createTime": {
        "type": "date",
        "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis||strict_date_optional_time"
      }
    }
  }
}
```