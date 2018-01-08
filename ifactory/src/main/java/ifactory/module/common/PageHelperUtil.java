package ifactory.module.common;

import org.springframework.util.StringUtils;

public class PageHelperUtil {
    public static String handleOrderBy(String sortColumn, String sortType) {
        String orderBy;
        if (StringUtils.isEmpty(sortColumn) || sortColumn.toLowerCase().equals("id")) {
            orderBy = "id DESC";
        }else if( sortColumn.toLowerCase().equals("a.id")){
        	orderBy = "a.id DESC";
        }else {
            if (StringUtils.isEmpty(sortType))
                orderBy = sortColumn + " ASC";
            else
                orderBy = sortColumn + " " + sortType;
        }
        return orderBy;
    }
}
