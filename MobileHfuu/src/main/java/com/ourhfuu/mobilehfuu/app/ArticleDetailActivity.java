package com.ourhfuu.mobilehfuu.app;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ourhfuu.mobilehfuu.entity.Article;
import com.ourhfuu.mobilehfuu.util.CToast;
import com.ourhfuu.mobilehfuu.util.StringUtil;
import com.ourhfuu.mobilehfuu.webservice.ArticleService;
import com.ourhfuu.mobilehfuu.webservice.parser.ArticleParser;
import com.ourhfuu.mobilehfuu.webservice.parser.ParserException;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 12/1/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleDetailActivity extends BaseActionBarActivity {
    private ArticleService mService;
    private TextView mTitle, mAuthor, mTime, mContent;
    private Article mArticle;
    private ArticleParser mParser;
    public static final String ARTICLE_INTENT = "article_intent";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);

        mTitle = (TextView) findViewById(R.id.a_name);
        mAuthor = (TextView) findViewById(R.id.a_author);
        mTime = (TextView) findViewById(R.id.a_time);
        mContent = (TextView) findViewById(R.id.a_content);

        mArticle = getIntent().getParcelableExtra(ARTICLE_INTENT);

        mAuthor.setText(mArticle.getUsername());
        mTitle.setText(mArticle.getTitle());
        mTime.setText(StringUtil.friendlyTime(mArticle.getDateline()));

        mParser = new ArticleParser();

        mService = new ArticleService(this);
        mService.getArticle(mArticle.getAid(), mListener, mErrorListenr);

    }


    @Override
    protected void setPageRecord() {
        mActivityName = "ArticleDetail";
        mIsPageRecord = true;
    }

    private Response.Listener<String> mListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                Article article = mParser.parse(response);
                    mArticle.setContent(article.getContent());
                    mContent.setText(Html.fromHtml(mArticle.getContent()));
            } catch (ParserException e) {
                CToast.showToast(ArticleDetailActivity.this, e.getMessage());
            }
        }
    };

    private Response.ErrorListener mErrorListenr = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            CToast.showToast(ArticleDetailActivity.this, error.getMessage());
        }
    };
}