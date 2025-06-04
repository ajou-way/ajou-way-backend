package com.ajouway.domain.enums;

import lombok.Getter;

@Getter
public enum MajorName {

    // 공과대학
    MECHANICAL_ENGINEERING("기계공학과"),
    ENVIRONMENTAL_SAFETY_ENGINEERING("환경안전공학과"),
    INDUSTRIAL_ENGINEERING("산업공학과"),
    CIVIL_SYSTEM_ENGINEERING("건설시스템공학과"),
    CHEMICAL_ENGINEERING("화학공학과"),
    TRANSPORTATION_SYSTEM_ENGINEERING("교통시스템공학과"),
    ADVANCED_MATERIALS_ENGINEERING("첨단신소재공학과"),
    ARCHITECTURE("건축학과 (건축학/건축공학전공)"),
    APPLIED_CHEMISTRY_AND_BIOENGINEERING("응용화학생명공학과"),
    CONVERGENCE_SYSTEM_ENGINEERING("융합시스템공학과"),
    APPLIED_CHEMISTRY("응용화학과"),

    // 첨단ICT융합대학
    ELECTRONIC_ENGINEERING("전자공학과"),
    INTELLIGENT_SEMICONDUCTOR_ENGINEERING("지능형반도체공학과"),
    FUTURE_MOBILITY_ENGINEERING("미래모빌리티공학과"),

    // 소프트웨어융합대학
    DIGITAL_MEDIA("디지털미디어학과"),
    SOFTWARE("소프트웨어학과"),
    DEFENSE_DIGITAL_CONVERGENCE("국방디지털융합학과"),
    CYBER_SECURITY("사이버보안학과"),
    ARTIFICIAL_INTELLIGENCE_CONVERGENCE("인공지능융합학과"),

    // 자연과학대학
    MATHEMATICS("수학과"),
    CHEMISTRY("화학과"),
    PHYSICS("물리학과"),
    LIFE_SCIENCES("생명과학과"),
    FRONTIER_SCIENCE("프런티어과학학부"),

    // 경영대학
    BUSINESS_ADMINISTRATION("경영학과"),
    FINANCIAL_ENGINEERING("금융공학과"),
    BUSINESS_INTELLIGENCE("경영인텔리전스학과"),
    GLOBAL_BUSINESS("글로벌경영학과"),

    // 인문대학
    KOREAN_LANGUAGE_AND_LITERATURE("국어국문학과"),
    HISTORY("사학과"),
    ENGLISH_LANGUAGE_AND_LITERATURE("영어영문학과"),
    CULTURAL_CONTENTS("문화콘텐츠학과"),
    FRENCH_LANGUAGE_AND_LITERATURE("불어불문학과"),

    // 사회과학대학
    ECONOMICS("경제학과"),
    SOCIOLOGY("사회학과"),
    PUBLIC_ADMINISTRATION("행정학과"),
    POLITICAL_SCIENCE_AND_DIPLOMACY("정치외교학과"),
    PSYCHOLOGY("심리학과"),
    SPORTS_AND_LEISURE("스포츠레저학과"),
    INTERDISCIPLINARY_SOCIAL_SCIENCE("경제정치사회융합학부"),

    // 의과대학
    MEDICINE("의학과"),

    // 간호대학
    NURSING("간호학과"),

    // 약학대학
    PHARMACY("약학과"),

    // 첨단바이오융합대학
    ADVANCED_BIO_CONVERGENCE("첨단바이오융합대학"),

    // 다산학부대학
    DASAN_COLLEGE("다산학부대학"),
    LIBERAL_ARTS("자유전공학부"),

    // 국제학부
    INTERNATIONAL_STUDIES("국제학부");

    private final String koreanName;

    MajorName(String koreanName) {
        this.koreanName = koreanName;
    }

    @Override
    public String toString() {
        return this.name(); // SOFTWARE
    }

}
