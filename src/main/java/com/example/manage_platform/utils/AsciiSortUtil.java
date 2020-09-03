package com.example.manage_platform.utils;

import org.apache.commons.beanutils.BeanComparator;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.*;

public class AsciiSortUtil {


    /**
     * 方法用途: 对所有传入参数按照字段名的  大排序（字典序），并且生成url参数串
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要编码
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return
     */
    public final static String AsciiSortFromSmallToBig(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
//                if (StringUtils.isNotBlank(item.getKey())) {
                if (item.getKey() != null) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

    /**
     * List 元素的多个属性进行排序。 元素在前，排序字段紧跟 propertie1，ASC，propertie2，DESC，
     * @param list       包含要排序元素的 List
     * @param properties 要排序的属性。前面的值优先级高。
     */
    public static <V> void sort(List<V> list, final String... properties) {
        Collections.sort(list, new Comparator<V>() {
            public int compare(V o1, V o2) {
                if (o1 == null && o2 == null) return 0;
                if (o1 == null) return -1;
                if (o2 == null) return 1;

                for (int i=0; i< properties.length-1 ;) {

                    Comparator c = new BeanComparator(properties[i]);
                    int result = 0;
                    if (properties[i+1].equals("ASC")){
                        result = c.compare(o1, o2);
                    } else {
                        result = c.compare(o2, o1);
                    }
                    i+=2;
                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            }
        });
    }


    @Test
    public void test() {
        //字典序列排序
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("jsapi_ticket", "sM4AOVdWfPE4DxkXGEs8VHDVW1dVoOmBZ5pRMQCMvRAB8i0kcTTqlvS1b2Ky0gIkEm6mJLLjjklL0V1U-qCTiQ");
        paraMap.put("url", "http://ace-wechat.amorepacific.com.cn/weixin/todo?&userId=A5487AE610AC409FD54BD43D523EF4A4");
        paraMap.put("noncestr", "Wm3WZYTPz0wzccnW");
        long timestamp = new Date().getTime();
        paraMap.put("timestamp", String.valueOf(timestamp));
        String url = AsciiSortFromSmallToBig(paraMap, false, true);
        System.out.println(url);
    }
}
