package com.cleber.financeiro.util;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

public class MaskUtil {

    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_FONE = "(##) #####-####";
    public static final String FORMAT_CARTAO = "#### #### #### ####";
    public static final String FORMAT_VALIDADE = "##/##";
    public static final String FORMAT_CVV = "###";
    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_DATE = "##/##/####";
    public static final String FORMAT_HOUR = "##:##";
    public static final String FORMAT_CONTA_BANK = "########-#";
    public static final String FORMAT_AGENCIA_BANK = "####";
    public static final String FORMAT_PARCELAS = "###";
    public static final String FORMAT_CODBAR_B = "#####.##### #####.###### #####.###### # ##############";
    public static final String FORMAT_CODBAR_C = "########### # ########### # ########### # ########### #";
    public static final String FORMAT_CODBAR_D = "################################################";
    public static String FORMAT_DEFAULT = "################################################";
    public static TextWatcher watcher;

    public static TextWatcher maskChangeable(final TextInputEditText ediTxt) {
        watcher = new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskUtil.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : FORMAT_DEFAULT.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
        return watcher;
    }

    public static TextWatcher mask2(final TextInputEditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskUtil.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]", "").replaceAll("[:]", "").replaceAll("[)]", "");
    }

    public static String unmask2(String str) {
        String sigs = "( ):/&$#@";
        for (int i = 0; i < sigs.length(); i++) {
            str = str.replaceAll(String.valueOf(sigs.charAt(i)), "");
        }
        return str;
    }
}
