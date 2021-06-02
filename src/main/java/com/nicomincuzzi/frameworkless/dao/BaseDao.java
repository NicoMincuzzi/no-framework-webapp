package com.nicomincuzzi.frameworkless.dao;

import java.util.List;

public abstract class BaseDao {
    private static final String MARKER = "@";

    public abstract void insert(ResultEntity resultEntity);

    public abstract List<ResultEntity> readBy(String userId) throws Exception;

    protected String buildQuery(String template, String... params) {
        StringBuilder buf = new StringBuilder();
        int lastMarker = 0;
        for (String param : params) {
            int curMarker = template.indexOf(MARKER, lastMarker);
            if (curMarker == -1)
                break;
            String prefix = template.substring(lastMarker, curMarker);
            buf.append(prefix);
            buf.append(param);
            lastMarker = curMarker + MARKER.length();
        }
        return buf.append(template.substring(lastMarker)).toString();
    }
}
