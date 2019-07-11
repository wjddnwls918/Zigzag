package com.example.zigzag.common

class BaseInfo {

    companion object {
        val ageType: HashMap<Int, String> by lazy {

            hashMapOf(
                0 to "10대",
                1 to "20대 초반",
                2 to "20대 중반",
                3 to "20대 후반",
                4 to "30대 초반",
                5 to "30대 중반",
                6 to "30대 후반"
            )
        }


        val styleTypeStringToNum: HashMap<String, Int> by lazy {

            hashMapOf(
                "페미닌" to 0,
                "모던시크" to 1,
                "심플베이직" to 2,
                "러블리" to 3,
                "유니크" to 4,
                "미시스타일" to 5,
                "캠퍼스룩" to 6,
                "빈티지" to 7,
                "섹시글램" to 8,
                "스쿨룩" to 9,
                "로맨틱" to 10,
                "오피스룩" to 11,
                "럭셔리" to 12,
                "헐리웃스타일" to 13
            )

        }

        val styleTypeNumToString: HashMap<Int, String> by lazy {

            hashMapOf<Int, String>(
                0 to "페미닌",
                1 to "모던시크",
                2 to "심플베이직",
                3 to "러블리",
                4 to "유니크",
                5 to "미시스타일",
                6 to "캠퍼스룩",
                7 to "빈티지",
                8 to "섹시글램",
                9 to "스쿨룩",
                10 to "로맨틱",
                11 to "오피스룩",
                12 to "럭셔리",
                13 to "헐리웃스타일"
            )

        }

    }
}