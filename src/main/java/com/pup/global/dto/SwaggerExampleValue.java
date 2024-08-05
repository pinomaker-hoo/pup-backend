package com.pup.global.dto;

public class SwaggerExampleValue {
    public static final String INTERNAL_SERVER_ERROR_RESPONSE = "{\"code\":401,\"status\":\"INTERNAL_SERVER\",\"message\":\"서버에서 오류가 발생했습니다.\"}";
    public static final String UN_AUTHENTICATION_RESPONSE = "{\"code\":401,\"status\":\"UNAUTHORIZED\",\"message\":\"만료된 JWT 토큰입니다.\"}";

    // ** DELETE : /api/v1/dog/{}
    public static final String DELETE_DOG_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"message\":\"강아지를 삭제합니다.\"}";

    // ** PUT : /api/v1/dog/{}
    public static final String UPDATE_DOG_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"message\":\"강아지를 수정 합니다.\"}";

    // ** POST : /api/v1/auth
    public static final String USER_SAVE_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"message\":\"회원가입에 성공하였습니다.\"}";
    public static final String USER_SAVE_EXISTED_NUMBER_RESPONSE = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"이미 사용 중인 아이디 입니다.\"}";

    // ** POST : /api/v1/auth/login
    public static final String USER_LOGIN_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"data\":{\"userId\":4,\"email\":\"test@test.com\", \"userUid\":\"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\",\"token\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiJ9.eyJ1c2VySWQiOiI1UGcwMlBjSEdlaWt1LWZERnJ2a2VBIiwidXNlclVpZCI6IkFDczE3RkV5RzAzbDdPcmtMMDF3ajlJeTFjQjc4cGNVTldnNmNmRkJweFpJV05hVU1oUWRxNlRpdXdOT1RRMWIiLCJpYXQiOjE3MjA4ODQ3MDIsImV4cCI6MTcyMDg4NjUwMn0.Wn2hCQmtrTXhDiqp__AMF6VQGeA_C0-9umXC6I_NkdhYcbhipDPirJf8epQ2CkDpzQIgp7z60JPtM67dVXD586f_Fs0g9q5hey8qbQBdSWx1CMpIDSDRt3smMhxDDjr7wkcABnklT_Jjamlc2FnHz1aaRj1h50PEShsJ8OsibaIHC5mUpHA2wZroEyS8niFbiZ5yXqtK7igkUrAs0bPs1AJnwFL6I3brcZGnDJGrk0mjh4z-3yusHjV8OXD5qy9rGKeaomuG48S3ji_Sx0HiMI3Q9Zs9AP0-hajBfzoZJ9kXPAzL7a_GsbUL-fOzDax2m02YQXF9C5GqyTxZYTpCYw\",\"refreshToken\":\"eyJhbGciOiJSUzI1NiJ9.eyJ1c2VySWQiOiI1UGcwMlBjSEdlaWt1LWZERnJ2a2VBIiwidXNlclVpZCI6IkFDczE3RkV5RzAzbDdPcmtMMDF3ajlJeTFjQjc4cGNVTldnNmNmRkJweFpJV05hVU1oUWRxNlRpdXdOT1RRMWIiLCJpYXQiOjE3MjA4ODQ3MDIsImV4cCI6MTcyMTQ4OTUwMn0.RIQSG4xE1YBUlZgdelQ2j1WBarD5uqfcLHu3rO3Ok8btGamfYwRxSoTh1es2RFIaxOwaS_CPSctopxLnzHDwoq1vSsR3Dm6PfM8EChmyFaEpYrVq0dqW-2kW-Cx2mfG9inkoI7YkgdTDNR54t3LTJ8awN82ULrtMRHfZZCEaiF5ZRMVRgp2yoqz9_Dzc7sIpxxSQWN9QaM1x40-bSrsVjE7nHdfGUjbgCuF4BNWoJjusaDilNy4FCgbrNFR6sXs5H_0owHrwkhbzdurgEiMFRbnNVweZbAb9M2YJpC3pUDgcK2l7gIXRCWqI1RP8rv83uZOBKhgr5ln9eUJc66MKmQ\"}},\"message\":\"로그인에 성공하였습니다.\"}";
    public static final String USER_LOGIN_NOT_FOUND_RESPONSE = "{\"code\":404,\"status\":\"NOT_FOUND\",\"message\":\"존재하지 않는 사용자 입니다.\"}";
    public static final String USER_LOGIN_NOT_MATCH_PASSWORD_RESPONSE = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"비밀번호가 일치하지 않습니다.\"}";

    // ** POST : /api/v1/auth/reissue
    public static final String REISSUE_TOKEN_RESPONSE = "{\"code\": 200, \"status\": \"OK\", \"data\": {\"accessToken\": \"eyJhbGciOiJSUzI1NiJ9.eyJpZCI6Ind0NFkzaWhmZGpEWFdITzR0c3UyUnciLCJpYXQiOjE3MTY3NDQ5NjAsImV4cCI6MTcxNjc0Njc2MH0.WLstl9tEgsoqRUL2W_YPrvBS45jIU6rmMgvv_hAsTcRJJsGs9gUq0eJcwjdNpFUO6HPyvCMr76aST9jUIt6x7SA5IzXyB7TgX0fZ2_4DyGvn6oeZ9c8kXswSny_SmPVlBuivllC2PfxLAy1niJwQyw2VYWCl6HbkUyTyr9Dx5gXGP2KQk92XEEg4uV-JoDVHHTWIKgCa60xL3KRDFTmJPhTh2lC91GzZG92wULmkrcmTGphAcVNwwF32SZcgjhvrk1FwwOoIf4yXuzmJs0KKPaR3WJJdjunlH7BNIrHyhXPPbl2ltEV7xO2E7O7XJbx8mdsoAYpDo5Jz2qPPs8kJOQ\"}, \"message\": \"토큰 재발급에 성공합니다.\"}";

    // ** GET : /api/v1/user
    public static final String FIND_USER = "{\"code\": 200, \"status\": \"OK\", \"data\": {\"userId\": 4, \"email\": \"test@test.com\",  \"userUid\": \"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\", \"profile\": \"https://123.png\", \"description\": \"안녕하세요. 저는 000 입니다.\"}, \"message\": \"유저 정보를 조회합니다.\"}";

    // ** GET : /api/v1/user/{}
    public static final String FIND_USER_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"data\":{\"userId\":4,\"email\":\"test@test.com\", \"userUid\":\"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\",\"profile\":\"https://123.png\",\"description\":\"안녕하세요. 저는 000 입니다.\",\"isFriend\":false},\"message\":\"유저 정보를 조회합니다.\"}";
    public static final String NOT_FOUND_USER = "{\"code\":404,\"status\":\"NOT_FOUND\",\"message\":\"유저 정보를 찾을 수 없습니다.\"}";

    // ** GET : /api/v1/user/search
    public static final String FIND_USER_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"userId\":6,\"userUid\":\"b758c502-049e-42ab-ac19-95b9f7524e59\",\"profile\":\"\",\"dogProfileList\":[\"https://\"]},{\"userId\":7,\"userUid\":\"66d33aab-674d-4dc4-98aa-01ceeadd0989\",\"profile\":\"\",\"dogProfileList\":[]}],\"message\":\"친구 대상 리스트를 조회합니다.\"}";

    // ** PUT : /api/v1/user
    public static final String UPDATE_USER = "{\"code\": 200, \"status\": \"OK\", \"message\": \"유저 정보를 수정합니다.\"}";

    // ** PATCH : /api/v1/user/password
    public static final String UPDATE_USER_PASSWORD = "{\"code\": 200, \"status\": \"OK\", \"message\": \"유저 비밀번호를 수정합니다.\"}";

    // ** POST : /api/v1/dog
    public static final String SAVE_DOG = "{\"code\": 200, \"status\": \"OK\", \"message\": \"강아지 저장에 성공 했습니다.\"}";

    // ** GET : /api/v1/dog
    public static final String FIND_DOG_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"dogId\":1,\"name\":\"뽀삐\",\"profile\":\"https://\",\"birth\":\"2021-01-01\",\"isNeutered\":false}],\"message\":\"강아지 리스트를 조회합니다.\"}";

    // ** PATCH : /api/v1/walking-trail/expose
    public static final String EXPOSE_WALKING_TRAIL = "{\"code\":200,\"s유tatus\":\"OK\",\"message\":\"산책로를 공개합니다.\"}";

    // ** PATCH : /api/v1/walking-trail/dog
    public static final String UPDATE_WALKING_TRAIL_DOG = "{\"code\":200,\"status\":\"OK\",\"message\":\"산책로의 강아지를 수정합니다.\"}";

    // ** POST : /api/v1/walking-trail
    public static final String SAVE_WALKING_TRAIL = "{\"code\":200,\"status\":\"OK\",\"data\":\"93e1ce84-4e96-468b-b21e-7622f9cc0e42\",\"message\":\"산책로를 생성합니다.\"}";
    public static final String NOT_FOUND_DOG_RESPONSE = "{\"code\":404,\"status\":\"NOT_FOUND\",\"message\":\"강아지를 찾을 수 없습니다.\"}";

    // ** PATCH : /api/v1/walking-trail/like/{}
    public static final String SAVE_WALKING_TRAIL_LIKE = "{\"code\":200,\"status\":\"OK\",\"message\":\"산책로에 좋아요를 누르거나 취소합니다.\"}";
    public static final String NOT_FOUND_WALKING_TRAIL_LIKE = "{\"code\":404,\"status\":\"NOT_FOUND\",\"message\":\"좋아요를 누르지 않았습니다.\"}";
    public static final String EXISTED_WALKING_TRAIL_LIKE = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"이미 좋아요를 누르셨습니다.\"}";

    // ** GET : /api/v1/walking-trail
    public static final String FIND_WALKING_LIST = "{\"code\": 200, \"status\": \"OK\", \"data\": [{\"walkingTrailId\": 4, \"mainImage\": null, \"name\": null, \"description\": null, \"walkingTrailUid\": \"93e1ce84-4e96-468b-b21e-7622f9cc0e42\", \"time\": 0, \"distance\": 0, \"openRange\": null, \"createdDate\": \"2024-07-18T00:40:02.808216\", \"rating\": 2, \"itemList\": [{\"walkingTrailItemId\": 3, \"lat\": 12.121231, \"lng\": 12.121231}, {\"walkingTrailItemId\": 4, \"lat\": 12.121231, \"lng\": 12.121231}, {\"walkingTrailItemId\": 5, \"lat\": 12.121231, \"lng\": 12.121231}, {\"walkingTrailItemId\": 6, \"lat\": 12.121231, \"lng\": 12.121231}, {\"walkingTrailItemId\": 7, \"lat\": 12.121231, \"lng\": 12.121231}, {\"walkingTrailItemId\": 8, \"lat\": 12.121231, \"lng\": 12.121231}, {\"walkingTrailItemId\": 9, \"lat\": 12.121231, \"lng\": 12.121231}]}], \"message\": \"나의 산책로 리스트를 조회합니다.\"}";

    // ** GET : /api/v1/walking-trail/{}
    public static final String FIND_WALKING = "{\"code\":200,\"status\":\"OK\",\"data\":{\"walkingTrailId\":4,\"mainImage\":null,\"name\":null,\"description\":null,\"walkingTrailUid\":\"93e1ce84-4e96-468b-b21e-7622f9cc0e42\",\"time\":0,\"distance\":0,\"openRange\":null,\"createdDate\":\"2024-07-18T00:40:02.808216\",\"rating\":2,\"userId\":4,\"reviewCount\":2,\"likeCount\":0,\"isLike\":false,\"itemList\":[{\"walkingTrailItemId\":3,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":4,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":5,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":6,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":7,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":8,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":9,\"lat\":12.121231,\"lng\":12.121231}],\"imageList\":[\"https://123.png\"]},\"message\":\"산책로를 조회합니다.\"}";

    // ** GET : /api/v1/walking-trail/like
    public static final String FIND_LIKE_WALKING_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"walkingTrailLikeId\":14,\"mainImage\":\"\",\"walkingTrailId\":103,\"name\":\"작성해 주세요\",\"description\":\"테스트\",\"walkingTrailUid\":\"1ebe003d-da48-4c19-8316-011c8ad74e02\",\"time\":6,\"distance\":0,\"openRange\":\"PRIVATE\",\"createdDate\":\"2024-07-29T14:12:54.255007\",\"rating\":null,\"userId\":15,\"reviewCount\":0,\"likeCount\":8,\"itemList\":[{\"walkingTrailItemId\":222,\"lat\":37.400414,\"lng\":127.10487}]},{\"walkingTrailLikeId\":5,\"mainImage\":null,\"walkingTrailId\":11,\"name\":\"서울시 어쩌구\",\"description\":\"해당 산책에 대한 기록을 저장합니다.\",\"walkingTrailUid\":\"cf2c63cc-8b06-4b3d-8b2d-30f78be5ada1\",\"time\":120,\"distance\":0,\"openRange\":\"PUBLIC\",\"createdDate\":\"2024-07-21T12:54:17.601\",\"rating\":2,\"userId\":4,\"reviewCount\":2,\"likeCount\":8,\"itemList\":[]},{\"walkingTrailLikeId\":1,\"mainImage\":null,\"walkingTrailId\":9,\"name\":\"서울시 어쩌구\",\"description\":\"해당 산책에 대한 기록을 저장합니다.\",\"walkingTrailUid\":\"cf2c63cc-8b06-4b3d-8b2d-30f78be5ada3\",\"time\":120,\"distance\":0,\"openRange\":\"PUBLIC\",\"createdDate\":\"2024-07-21T12:52:17.601437\",\"rating\":null,\"userId\":4,\"reviewCount\":0,\"likeCount\":8,\"itemList\":[{\"walkingTrailItemId\":24,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":25,\"lat\":12.121231,\"lng\":12.121231}]}],\"message\":\"찜한 산책로 리스트를 조회합니다.\"}";

    // ** GET : /api/v1/walking-trail/search
    public static final String SEARCH_WALKING_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"walkingTrailId\":11,\"mainImage\":null,\"name\":\"서울시 어쩌구\",\"description\":\"해당 산책에 대한 기록을 저장합니다.\",\"walkingTrailUid\":\"cf2c63cc-8b06-4b3d-8b2d-30f78be5ada1\",\"time\":120,\"distance\":0,\"openRange\":\"PUBLIC\",\"createdDate\":\"2024-07-21T12:54:17.601\",\"rating\":2,\"userId\":6,\"userUid\":\"b758c502-049e-42ab-ac19-95b9f7524e59\",\"reviewCount\":1,\"likeCount\":1,\"isLike\":true,\"itemList\":[]},{\"walkingTrailId\":10,\"mainImage\":null,\"name\":\"서울시 어쩌구\",\"description\":\"해당 산책에 대한 기록을 저장합니다.\",\"walkingTrailUid\":\"cf2c63cc-8b06-4b3d-8b2d-30f78be5ada2\",\"time\":120,\"distance\":0,\"openRange\":\"PUBLIC\",\"createdDate\":\"2024-07-21T12:53:17.601\",\"rating\":2,\"userId\":6,\"userUid\":\"b758c502-049e-42ab-ac19-95b9f7524e59\",\"reviewCount\":2,\"likeCount\":0,\"isLike\":false,\"itemList\":[]},{\"walkingTrailId\":9,\"mainImage\":null,\"name\":\"서울시 어쩌구\",\"description\":\"해당 산책에 대한 기록을 저장합니다.\",\"walkingTrailUid\":\"cf2c63cc-8b06-4b3d-8b2d-30f78be5ada3\",\"time\":120,\"distance\":0,\"openRange\":\"PUBLIC\",\"createdDate\":\"2024-07-21T12:52:17.601437\",\"rating\":null,\"userId\":6,\"userUid\":\"b758c502-049e-42ab-ac19-95b9f7524e59\",\"reviewCount\":0,\"likeCount\":1,\"isLike\":true,\"itemList\":[{\"walkingTrailItemId\":24,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":25,\"lat\":12.121231,\"lng\":12.121231}]}],\"message\":\"산책로 리스트를 조회합니다.\"}";

    // ** POST : /api/v1/walking-trail/item
    public static final String SAVE_WALKING_TRAIL_ITEM = "{\"code\":200,\"status\":\"OK\", \"message\":\"산책로 지점을 생성합니다.\"}";
    public static final String NOT_FOUND_WALKING_TRAIL = "{\"code\":404,\"status\":\"NOT_FOUND\",\"message\":\"산책로를 찾을 수 없습니다.\"}";
    public static final String EXIST_WALKING_TRAIL_ITEM = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"이미 활성화된 산책로입니다.\"}";

    // ** POST : /api/v1/walking-trail/image
    public static final String SAVE_WALKING_TRAIL_IMAGE = "{\"code\":200,\"status\":\"OK\", \"message\":\"산책로 이미지를 생성합니다.\"}";

    // ** POST : /api/v1/walking-trail/review
    public static final String SAVE_WALKING_TRAIL_REVIEW = "{\"code\":200,\"status\":\"OK\",\"message\":\"산책로를 리뷰 합니다.\"}";

    // ** GET : /api/v1/walking-trail/review
    public static final String FIND_REVIEW_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"walkingTrailReviewId\":3,\"mainImage\":null,\"walkingTrailId\":4,\"name\":null,\"description\":null,\"walkingTrailUid\":\"93e1ce84-4e96-468b-b21e-7622f9cc0e42\",\"time\":0,\"distance\":0,\"createdDate\":\"2024-07-18T21:27:39.387889\",\"rating\":2.3333333333333335,\"userId\":4,\"userUid\":\"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\",\"reviewCount\":9},{\"walkingTrailReviewId\":5,\"mainImage\":null,\"walkingTrailId\":7,\"name\":\"서울시 어쩌구\",\"description\":\"해당 산책에 대한 기록을 저장합니다.\",\"walkingTrailUid\":\"aeeae8d7-ba6f-4562-af18-3a3943486545\",\"time\":0,\"distance\":0,\"createdDate\":\"2024-07-18T21:27:39.387889\",\"rating\":2.3333333333333335,\"userId\":4,\"userUid\":\"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\",\"reviewCount\":9},{\"walkingTrailReviewId\":6,\"mainImage\":null,\"walkingTrailId\":10,\"name\":\"서울시 어쩌구\",\"description\":\"해당 산책에 대한 기록을 저장합니다.\",\"walkingTrailUid\":\"cf2c63cc-8b06-4b3d-8b2d-30f78be5ada2\",\"time\":0,\"distance\":0,\"createdDate\":\"2024-07-18T21:27:39.387889\",\"rating\":2.3333333333333335,\"userId\":6,\"userUid\":\"b758c502-049e-42ab-ac19-95b9f7524e59\",\"reviewCount\":9}],\"message\":\"나의 산책로 리뷰 리스트 조회 합니다.\"}";

    // ** GET : /api/v1/walking-trail/review/{}
    public static final String FIND_USER_REVIEW_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"walkingTrailReviewId\":3,\"mainImage\":null,\"walkingTrailId\":4,\"name\":null,\"description\":null,\"walkingTrailUid\":\"93e1ce84-4e96-468b-b21e-7622f9cc0e42\",\"time\":0,\"distance\":0,\"createdDate\":\"2024-07-18T21:27:39.387889\",\"rating\":2.857142857142857,\"userId\":4,\"userUid\":\"7b4e6ef6-c29b-4c58-9787-7fcae9a7babb\",\"reviewCount\":14,\"itemList\":[{\"walkingTrailItemId\":3,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":4,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":5,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":6,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":7,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":8,\"lat\":12.121231,\"lng\":12.121231},{\"walkingTrailItemId\":9,\"lat\":12.121231,\"lng\":12.121231}]}],\"message\":\"유저의 산책로 리뷰 리스트 조회 합니다.\"}";

    // ** GET : /api/v1/friend
    public static final String FIND_FRIEND_LIST = "{\"code\":200,\"status\":\"OK\",\"data\":[{\"friendId\":2,\"userId\":5,\"userUid\":\"bce240eb-c8af-47ca-b783-d72954e8adbc\",\"description\":\"\",\"profile\":\"\",\"lastWakingDate\":null,\"dogProfileList\":[\"123123123\",\"https://\"]}],\"message\":\"친구 리스트를 조회합니다.\"}";

    // ** POST : /api/v1/friend
    public static final String EXISTED_FRIEND_RESPONSE = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"이미 친구로 등록된 사용자 입니다.\"}";
    public static final String SAVE_FRIEND_RESPONSE = "{\"code\":200,\"status\":\"OK\",\"message\":\"친구를 등록합니다.\"}";
    public static final String NOT_FOUND_USER_RESPONSE = "{\"code\":404,\"status\":\"NOT_FOUND\",\"message\":\"존재하지 않는 사용자 입니다.\"}";
    public static final String NOT_SELF_SAVE_FRIEND_RESPONSE = "{\"code\":400,\"status\":\"BAD_REQUEST\",\"message\":\"자기 자신을 친구로 등록할 수 없습니다.\"}";

    // ** DELETE : /api/v1/friend
    public static final String DELETE_FRIEND_LIST = "{\"code\":200,\"status\":\"OK\",\"message\":\"친구를 삭제합니다.\"}";

    // ** DELETE : /api/v1/walking-trail
    public static final String DELETE_WALKING_TRAIL_LIST = "{\"code\":200,\"status\":\"OK\",\"message\":\"산책로를 삭제합니다.\"}";
}
