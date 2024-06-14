# flyway 실행 후 오류가 발생했다면 아래 쿼리를 실행하여 실패한 이력을 삭제하고 다시 실행하면 된다.
DELETE FROM flyway_schema_history WHERE success=false;