package ru.job4j.pooh;

import java.util.Arrays;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        if (content.contains("POST")) {
            String[] parse = content.split(" ");
            String[] type = parse[1].split("/");
            String[] p = parse[7].split(System.lineSeparator());
            String param = p[p.length - 1];
            return new Req(parse[0], type[1], type[2], param);
        }
        String[] parse = content.split(" ");
        String[] type = parse[1].split("/");
        System.out.println(Arrays.asList(type));
        String param = type.length < 4 ? "" : type[3];
        return new Req(parse[0], type[1], type[2], param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "Req{" + "httpRequestType='" + httpRequestType + '\''
                + ", poohMode='" + poohMode + '\''
                + ", sourceName='" + sourceName + '\''
                + ", param='" + param + '\'' + '}';
    }
}
