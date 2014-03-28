package com.ourhfuu.mobilehfuu.app;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 12/1/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleDetailActivity extends BaseActionBarActivity {
    //private ArticleService mArticleService;
    private TextView mTitle, mAuthor, mTime, mContent;
    //private Article mArticle;
    public static final String ARTICLE_INTENT = "article_intent";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.article_detail);

//        mTitle = (TextView) findViewById(R.id.a_name);
//        mAuthor = (TextView) findViewById(R.id.a_author);
//        mTime = (TextView) findViewById(R.id.a_time);
//        mContent = (TextView) findViewById(R.id.a_content);
//
//        mArticle = getIntent().getParcelableExtra(ARTICLE_INTENT);
//
//        mAuthor.setText(mArticle.getUsername());
//        mTitle.setText(mArticle.getTitle());
//        mTime.setText(String.valueOf(mArticle.getDateline()));
//
//        mArticleService = new ArticleService(this);
//        mArticleService.getArticle(mArticle.getAid(), new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                ArticleParser parser = new ArticleParser();
//                try {
//                    Article article = parser.parse(responseString);
//                    mArticle.setContent(article.getContent());
//                    mContent.setText(Html.fromHtml(mArticle.getContent()));
//                } catch (ParserException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//            }
//        });

    }


    @Override
    protected void setPageRecord() {
        mActivityName = "ArticleDetail";
        mIsPageRecord = true;
    }
}