package edu.tstc.yy;

/**
 * Created by w_2 on 2016-09-07.
 */
public class ReturnCode {
    public static final String USER_REGISTER_SUCCESS="1000";
    public static final String USER_REGISTER_FAILURE="1001";
    public static final String USER_REGISTER_SAMEUSERNAME="1002";
    public static final String USER_REGISTER_SAMENICKNAME="1003";

    public static final String USER_SAMENAME_FALSE="1010";
    public static final String USER_SAMENAME_TRUE="1011";

    public static final String USER_LOGIN_SUCCESS="1020";
    public static final String USER_LOGIN_NOUSERNAME="1021";
    public static final String USER_LOGIN_FAILURE="1022";

    public static final String USER_ALTERPASSWORD_SUCCESS="1030";
    public static final String USER_ALTERPASSWORD_FAILURE="1031";

    public static final String USERINFO_INSERT_SUCCESS="2000";
    public static final String USERINFO_INSERT_FAILURE="2001";
    public static final String USERINFO_SEXCODE_ERROR="2002";
    public static final String USERINFO_SEXCODE_NULL="2003";
    public static final String USERINFO_GETINFO_SUCCESS="2010";
    public static final String USERINFO_GETINFO_FAILURE="2011";


    public static final String ARTICLE_SELECT_SUCCESS="3000";
    public static final String ARTICLE_SELECT_FAILURE="3001";
    public static final String ARTICLE_SELECT_BY_USER_ARTICLE_SELECT_SUCCESS="3010";
    public static final String ARTICLE_SELECT_BY_USER_FAILURE="3011";
    public static final String ARTICLE_SELECT_DETAIL_SUCCESS="3020";
    public static final String ARTICLE_SELECT_DETAIL_FAILURE="3021";
    public static final String ARTICLE_INSERT_SUCCESS="3030";
    public static final String ARTICLE_INSERT_FAILURE="3031";
    public static final String ARTICLE_INSERT_FAILURE_ILLICIT_USERID="3032";
    public static final String ARTICLE_DELETE_SUCCESS="3040";
    public static final String ARTICLE_DELETE_FAILURE="3041";
    public static final String ARTICLE_EDIT_SUCCESS="3050";
    public static final String ARTICLE_EDIT_FAILURE="3051";



    public static final String COMMENT_INSERT_SUCCESS="4000";
    public static final String COMMENT_INSERT_FAILURE="4001";
    public static final String COMMENT_SELECTMORE_SUCCESS="4010";
    public static final String COMMENT_SELECTMORE_NOMORE="4011";
    public static final String COMMENT_DELETE_SUCCESS="4020";
    public static final String COMMENT_DELETE_FAILURE="4021";
    public static final String COMMENT_EDIT_SUCCESS="4030";
    public static final String COMMENT_EDIT_FAILURE="4031";

    public static final String NULLVALUE="101001";
    public static final String ERROR="101002";
    public static final String USER_ROLE_ERROR="101003";
    public static final String USER_NOT_AUTHOR="101004";


    public static final String IMAGE_UPLOAD_ERROR="101005";

}
