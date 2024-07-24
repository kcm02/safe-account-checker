package com.project.safe_account.util;

import com.project.safe_account.dto.ValidateResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AccountNumberValidatorUtil {

    private static final Map<String, String[]> patterns = new HashMap<>();
    private static final Map<String, Integer[]> lengths = new HashMap<>();

    static {
        // 각 은행별 자유적금계좌 패턴
        patterns.put("국민", new String[]{ "\\d{4}(03|23|26)\\d{8}" });
        patterns.put("신한", new String[]{ "(230|223)\\d{9}" });
        patterns.put("우리", new String[]{ "^\\d{1}040.*" });
        patterns.put("하나", new String[]{ "\\d{12}(21|25)" });
        patterns.put("농협", new String[]{
                "\\d{3}(04|34|47|49|59)\\d{6}",
                "\\d{4}(04|34|47|49|59)\\d{6}",
                "(304|334|347|349|359|004|034|047|049|059)\\d{10}"
        });
        patterns.put("수협", new String[]{ "(1400|1410)\\d{8}" });
        patterns.put("기업", new String[]{ "\\d{9}14\\d{3}" });
        patterns.put("산업", new String[]{ "(031|032|037)\\d{11}" });
        patterns.put("카카오", new String[]{ "^\\d{1}355.*" });
        patterns.put("케이", new String[]{ "1102\\d{8}" });
        patterns.put("토스", new String[]{ "300\\d{9}" });
        patterns.put("경남", new String[]{ "(225|229|231|241)\\d{10}" });
        patterns.put("광주", new String[]{
                "\\d{3}133\\d{6}",
                "^\\d{1}.133.*"});
        patterns.put("대구", new String[]{ "(521|257)\\d{9}" });
        patterns.put("부산", new String[]{ "104\\d{10}" });
        patterns.put("전북", new String[]{ "1031\\d{9}" });
        patterns.put("제주", new String[]{
                "\\d{1}(07|20)\\d{7}",
                "(730|740)\\d{9}"
        });
        patterns.put("씨티", new String[]{
                "\\d{8}(16|18|19|20|37|38|39)\\d{1}",
                "\\d{8}(16|18|19|20|37|38|39)\\d{3}"
        });
        patterns.put("SC", new String[]{ "\\d{3}90\\d{6}" });

        // 각 은행별 계좌번호 길이
        lengths.put("국민", new Integer[]{14});
        lengths.put("신한", new Integer[]{12});
        lengths.put("우리", new Integer[]{13});
        lengths.put("하나", new Integer[]{14});
        lengths.put("농협", new Integer[]{11, 12, 13});
        lengths.put("수협", new Integer[]{12});
        lengths.put("기업", new Integer[]{14});
        lengths.put("산업", new Integer[]{14});
        lengths.put("카카오", new Integer[]{13});
        lengths.put("케이", new Integer[]{12});
        lengths.put("토스", new Integer[]{12});
        lengths.put("경남", new Integer[]{13});
        lengths.put("광주", new Integer[]{12, 13});
        lengths.put("대구", new Integer[]{12});
        lengths.put("부산", new Integer[]{13});
        lengths.put("전북", new Integer[]{13});
        lengths.put("제주", new Integer[]{10, 12});
        lengths.put("씨티", new Integer[]{11, 13});
        lengths.put("SC", new Integer[]{11});
    }

    public static ValidateResponse validateAccountNumber(String bankName, String accountNumber) {
        // 계좌 번호에서 하이픈 제거
        String cleanedAccountNumber = accountNumber.replaceAll("-", "");

        // 계좌 번호 길이 검증
        Integer[] validLengths = lengths.get(bankName);
        boolean lengthMatch = false;
        for (int length : validLengths) {
            if (cleanedAccountNumber.length() == length) {
                lengthMatch = true;
                break;
            }
        }
        if (!lengthMatch) {
            return new ValidateResponse(false, "올바른 계좌 형식이 아닙니다. 계좌 번호를 다시 한 번 확인해 주세요.");
        }

        // 패턴 검증
        for (String pattern : patterns.get(bankName)) {
            if (Pattern.matches(pattern, cleanedAccountNumber)) {
                return new ValidateResponse(true, "🚨 해당 계좌는 자유적금 계좌입니다. 거래에 유의하세요 🚨");
            }
        }
        return new ValidateResponse(false, "해당 계좌는 자유적금 계좌가 아닙니다. 안심하세요😊");
    }
}
