package com.pup.global.dto;

public class SwaggerExampleValue {
    public static final String INTERNAL_SERVER_ERROR_RESPONSE = "{\"code\":401,\"status\":\"INTERNAL_SERVER\",\"message\":\"서버에서 오류가 발생했습니다.\"}";
    public static final String UN_AUTHENTICATION_RESPONSE = "{\"code\":401,\"status\":\"UNAUTHORIZED\",\"message\":\"만료된 JWT 토큰입니다.\"}";

    // ** POST : /api/v1/auth
    public static final String USER_SAVE_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"message\":\"회원가입에 성공하였습니다.\"}";
    public static final String USER_SAVE_EXISTED_NUMBER_RESPONSE = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"이미 사용 중인 아이디 입니다.\"}";

    // ** POST : /api/v1/auth/login
    public static final String USER_LOGIN_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"data\":{\"userId\":4,\"email\":\"test@test.com\",\"nickname\":\"\",\"userUid\":\"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\",\"token\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiJ9.eyJ1c2VySWQiOiI1UGcwMlBjSEdlaWt1LWZERnJ2a2VBIiwidXNlclVpZCI6IkFDczE3RkV5RzAzbDdPcmtMMDF3ajlJeTFjQjc4cGNVTldnNmNmRkJweFpJV05hVU1oUWRxNlRpdXdOT1RRMWIiLCJpYXQiOjE3MjA4ODQ3MDIsImV4cCI6MTcyMDg4NjUwMn0.Wn2hCQmtrTXhDiqp__AMF6VQGeA_C0-9umXC6I_NkdhYcbhipDPirJf8epQ2CkDpzQIgp7z60JPtM67dVXD586f_Fs0g9q5hey8qbQBdSWx1CMpIDSDRt3smMhxDDjr7wkcABnklT_Jjamlc2FnHz1aaRj1h50PEShsJ8OsibaIHC5mUpHA2wZroEyS8niFbiZ5yXqtK7igkUrAs0bPs1AJnwFL6I3brcZGnDJGrk0mjh4z-3yusHjV8OXD5qy9rGKeaomuG48S3ji_Sx0HiMI3Q9Zs9AP0-hajBfzoZJ9kXPAzL7a_GsbUL-fOzDax2m02YQXF9C5GqyTxZYTpCYw\",\"refreshToken\":\"eyJhbGciOiJSUzI1NiJ9.eyJ1c2VySWQiOiI1UGcwMlBjSEdlaWt1LWZERnJ2a2VBIiwidXNlclVpZCI6IkFDczE3RkV5RzAzbDdPcmtMMDF3ajlJeTFjQjc4cGNVTldnNmNmRkJweFpJV05hVU1oUWRxNlRpdXdOT1RRMWIiLCJpYXQiOjE3MjA4ODQ3MDIsImV4cCI6MTcyMTQ4OTUwMn0.RIQSG4xE1YBUlZgdelQ2j1WBarD5uqfcLHu3rO3Ok8btGamfYwRxSoTh1es2RFIaxOwaS_CPSctopxLnzHDwoq1vSsR3Dm6PfM8EChmyFaEpYrVq0dqW-2kW-Cx2mfG9inkoI7YkgdTDNR54t3LTJ8awN82ULrtMRHfZZCEaiF5ZRMVRgp2yoqz9_Dzc7sIpxxSQWN9QaM1x40-bSrsVjE7nHdfGUjbgCuF4BNWoJjusaDilNy4FCgbrNFR6sXs5H_0owHrwkhbzdurgEiMFRbnNVweZbAb9M2YJpC3pUDgcK2l7gIXRCWqI1RP8rv83uZOBKhgr5ln9eUJc66MKmQ\"}},\"message\":\"로그인에 성공하였습니다.\"}";
    public static final String USER_LOGIN_NOT_FOUND_RESPONSE = "{\"code\":404,\"status\":\"NOT_FOUND\",\"message\":\"존재하지 않는 사용자 입니다.\"}";
    public static final String USER_LOGIN_NOT_MATCH_PASSWORD_RESPONSE = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"비밀번호가 일치하지 않습니다.\"}";

    // ** POST : /api/v1/auth/reissue
    public static final String REISSUE_TOKEN_RESPONSE = "{\"code\": 200, \"status\": \"OK\", \"data\": {\"accessToken\": \"eyJhbGciOiJSUzI1NiJ9.eyJpZCI6Ind0NFkzaWhmZGpEWFdITzR0c3UyUnciLCJpYXQiOjE3MTY3NDQ5NjAsImV4cCI6MTcxNjc0Njc2MH0.WLstl9tEgsoqRUL2W_YPrvBS45jIU6rmMgvv_hAsTcRJJsGs9gUq0eJcwjdNpFUO6HPyvCMr76aST9jUIt6x7SA5IzXyB7TgX0fZ2_4DyGvn6oeZ9c8kXswSny_SmPVlBuivllC2PfxLAy1niJwQyw2VYWCl6HbkUyTyr9Dx5gXGP2KQk92XEEg4uV-JoDVHHTWIKgCa60xL3KRDFTmJPhTh2lC91GzZG92wULmkrcmTGphAcVNwwF32SZcgjhvrk1FwwOoIf4yXuzmJs0KKPaR3WJJdjunlH7BNIrHyhXPPbl2ltEV7xO2E7O7XJbx8mdsoAYpDo5Jz2qPPs8kJOQ\"}, \"message\": \"토큰 재발급에 성공합니다.\"}";

    // ** GET : /api/v1/user
    public static final String FIND_USER = "{\"code\": 200, \"status\": \"OK\", \"data\": {\"userId\": 4, \"email\": \"test@test.com\", \"nickname\": \"\", \"userUid\": \"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\", \"profile\": \"https://123.png\", \"description\": \"안녕하세요. 저는 000 입니다.\"}, \"message\": \"유저 정보를 조회합니다.\"}";

    // ** PUT : /api/v1/user
    public static final String UPDATE_USER = "{\"code\": 200, \"status\": \"OK\", \"message\": \"유저 정보를 수정합니다.\"}";

    // ** PATCH : /api/v1/user/password
    public static final String UPDATE_USER_PASSWORD = "{\"code\": 200, \"status\": \"OK\", \"message\": \"유저 비밀번호를 수정합니다.\"}";

    // ** POST : /api/v1/dog
    public static final String SAVE_DOG = "{\"code\": 200, \"status\": \"OK\", \"message\": \"강아지 저장에 성공 했습니다.\"}";

    // ** GET : /api/v1/dog
    public static final String FIND_DOG_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"dogId\":1,\"name\":\"뽀삐\",\"profile\":\"https://\",\"birth\":\"2021-01-01\",\"isNeutered\":false}],\"message\":\"강아지 리스트를 조회합니다.\"}";
}
