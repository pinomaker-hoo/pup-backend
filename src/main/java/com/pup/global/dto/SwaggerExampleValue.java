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

    // ** POST : /api/v1/user/reissue
    public static final String REISSUE_TOKEN_RESPONSE = "{\"meta\":{\"code\":200,\"message\":\"토큰을 재발급 받습니다.\"},\"data\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiJ9.eyJyb2xlIjoiVUJPVE5VV1VlWUR4U2lraW1lWmZsUSIsImlkIjoiaEg5MzRaTEpYWWhFaEV1UERVb1NnQSIsImlhdCI6MTcwMjM4OTQxNSwiZXhwIjoxNzAyMzkxMjE1fQ.TBXT7FwC7F1HhQNiUnZcBS68B9T3SpI4pRPDug38CfIhz-69CUwhfCXruHo5I2yeMKCCWNHbpn5TS5pVEEgqGG_kG1ABinUvH2GCIMhvqoZf6iOTJqjbPxgA63VNoRtNfpHyMn_R1lgW75C-BUreyYSDcxVYGOh6oc5tB8jbfx6O6kRslp14xoX4BKEiOiTjJSIoCddRZY94ru8V7WhTXG6TK927AYZHXeoOuYUZqbDRJc2nxT-2KTM_MYuicnDZqo6XPgxkE4yS5SaSkQZqhUSyXD8nrFfNvckZdXhfItPkybPOYYuMqo4vaWuLndw71TkFqJzEBixTLLPewtJYcg\",\"refreshToken\":\"eyJhbGciOiJSUzI1NiJ9.eyJyb2xlIjoiVUJPVE5VV1VlWUR4U2lraW1lWmZsUSIsImlkIjoiaEg5MzRaTEpYWWhFaEV1UERVb1NnQSIsImlhdCI6MTcwMjM4OTQxNSwiZXhwIjoxNzAyOTk0MjE1fQ.ZrMcx1vLkGUSsRFKjEPBqd6L8XBLKr4hiXWgVx0y8Lu-V2uHtWrrfbt8kGPX_lz92v0GCBSP87z42o_Df5yjHagzyRDZL5n3o1evCQWLAvqJCluZtm3Q2FqBKUr1f7iTQj_viHoKE6xCFJ5A3nDGCzk8VwJ86RxDg5SQUX_R0ZhcW33n6c1PJTvaokJPH8VcbuYULkSy82ncYPeVSg6DotVhfeva6Sn15hrnE6tpAP6b9LAmSkZCJr-EXuo20cKXFrSkHZrIaplWT-TL9nZ5Q04G_zEapkFC8rIam0BNh04WgJlufsRIrE9Mrn3Ka27O-QAvRRE3bsva2eIBN18VWg\"}}";

    // ** POST : /api/v1/user
    public static final String SAMPLE_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"message\":\"샘플\"}";
}
