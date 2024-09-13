INSERT INTO p_hubs (id, name, city, latitude, longitude, address, created_at, is_delete) VALUES
                                                                              (gen_random_uuid(), '서울특별시 센터', '서울특별시', 37.475081089936, 127.12349734954, '서울특별시 송파구 송파대로 55', NOW(), false),
                                                                              (gen_random_uuid(), '경기 북부 센터', '경기도', 37.6583598, 126.8320201, '경기도 고양시 덕양구 권율대로 570', NOW(), false),
                                                                              (gen_random_uuid(), '경기 남부 센터', '경기도', 37.217551, 127.487038, '경기도 이천시 덕평로 257-21', NOW(), false),
                                                                              (gen_random_uuid(), '부산광역시 센터', '부산광역시', 35.117485, 129.045325, '부산 동구 중앙대로 206', NOW(), false),
                                                                              (gen_random_uuid(), '대구광역시 센터', '대구광역시', 35.882369, 128.608482, '대구 북구 태평로 161', NOW(), false),
                                                                              (gen_random_uuid(), '인천광역시 센터', '인천광역시', 37.447594, 126.707911, '인천 남동구 정각로 29', NOW(), false),
                                                                              (gen_random_uuid(), '광주광역시 센터', '광주광역시', 35.160408, 126.851304, '광주 서구 내방로 111', NOW(), false),
                                                                              (gen_random_uuid(), '대전광역시 센터', '대전광역시', 36.352215, 127.378924, '대전 서구 둔산로 100', NOW(), false),
                                                                              (gen_random_uuid(), '울산광역시 센터', '울산광역시', 35.540754, 129.311358, '울산 남구 중앙로 201', NOW(), false),
                                                                              (gen_random_uuid(), '세종특별자치시 센터', '세종특별자치시', 36.484819, 127.280354, '세종특별자치시 한누리대로 2130', NOW(), false),
                                                                              (gen_random_uuid(), '강원특별자치도 센터', '강원특별자치도', 37.866529, 127.732002, '강원특별자치도 춘천시 중앙로 1', NOW(), false),
                                                                              (gen_random_uuid(), '충청북도 센터', '충청북도', 36.634749, 127.483231, '충북 청주시 상당구 상당로 82', NOW(),false),
                                                                              (gen_random_uuid(), '충청남도 센터', '충청남도', 36.597226, 126.662158, '충남 홍성군 홍북읍 충남대로 21', NOW(), false),
                                                                              (gen_random_uuid(), '전북특별자치도 센터', '전북특별자치도', 35.810194, 127.113137, '전북특별자치도 전주시 완산구 효자로 225', NOW(), false),
                                                                              (gen_random_uuid(), '전라남도 센터', '전라남도', 34.808075, 126.450472, '전남 무안군 삼향읍 오룡길 1', NOW(), false),
                                                                              (gen_random_uuid(), '경상북도 센터', '경상북도', 36.568354, 128.725448, '경북 안동시 풍천면 도청대로 455', NOW(), false),
                                                                              (gen_random_uuid(), '경상남도 센터', '경상남도', 35.238583, 128.655771, '경남 창원시 의창구 중앙대로 300', NOW(), false);

INSERT INTO p_hub_paths (id, start_hub_id, end_hub_id, travel_time, display_name, is_delete, created_at)
SELECT
    gen_random_uuid(),
    start_hub.id,
    end_hub.id,
    '90000000',  -- 문자열 형식으로 이동 시간 설정
    CONCAT(start_hub.name, ' -> ', end_hub.name),
    false,
    now()
FROM
    p_hubs start_hub
        JOIN p_hubs end_hub
             ON start_hub.id <> end_hub.id
WHERE
   -- 원하시는 이동 경로의 규칙을 정의합니다.
    (start_hub.name = '서울특별시 센터' AND end_hub.name = '경기 북부 센터') OR
    (start_hub.name = '경기 북부 센터' AND end_hub.name = '서울특별시 센터') OR
    (start_hub.name = '경기 북부 센터' AND end_hub.name = '경기 남부 센터') OR
    (start_hub.name = '경기 남부 센터' AND end_hub.name = '경기 북부 센터') OR
    (start_hub.name = '경기 남부 센터' AND end_hub.name = '부산광역시 센터') OR
    (start_hub.name = '부산광역시 센터' AND end_hub.name = '경기 남부 센터') OR
    (start_hub.name = '전라남도 센터' AND end_hub.name = '전북특별자치도 센터') OR
    (start_hub.name = '전북특별자치도 센터' AND end_hub.name = '전라남도 센터') OR
    (start_hub.name = '전북특별자치도 센터' AND end_hub.name = '충청남도 센터') OR
    (start_hub.name = '충청남도 센터' AND end_hub.name = '전북특별자치도 센터') OR
    (start_hub.name = '충청남도 센터' AND end_hub.name = '충청북도 센터') OR
    (start_hub.name = '충청북도 센터' AND end_hub.name = '충청남도 센터') OR
    (start_hub.name = '충청북도 센터' AND end_hub.name = '강원특별자치도 센터') OR
    (start_hub.name = '강원특별자치도 센터' AND end_hub.name = '충청북도 센터');

CREATE EXTENSION IF NOT EXISTS "pgcrypto";








