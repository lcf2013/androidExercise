package com.zhy.csdndemo;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.zhy.bean.CommonException;
import com.zhy.bean.NewsItem;
import com.zhy.biz.NewsItemBiz;
import com.zhy.csdn.Constaint;
import com.zhy.csdndemo.adapter.NewsItemAdapter;
import com.zhy.csdndemo.dao.NewsItemDao;
import com.zhy.csdndemo.util.AppUtil;
import com.zhy.csdndemo.util.Logger;
import com.zhy.csdndemo.util.NetUtil;
import com.zhy.csdndemo.util.ToastUtil;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements IXListViewRefreshListener, IXListViewLoadMore
{
	private static final int LOAD_MORE = 0x110;
	private static final int LOAD_REFREASH = 0x111;

	private static final int TIP_ERROR_NO_NETWORK = 0X112;
	private static final int TIP_ERROR_SERVER = 0X113;

	/**
	 * �Ƿ��ǵ�һ�ν���
	 */
	private boolean isFirstIn = true;

	/**
	 * �Ƿ���������
	 */
	private boolean isConnNet = false;

	/**
	 * ��ǰ�����Ƿ��Ǵ������л�ȡ��
	 */
	private boolean isLoadingDataFromNetWork;

	/**
	 * Ĭ�ϵ�newType
	 */
	private int newsType = Constaint.NEWS_TYPE_YEJIE;
	/**
	 * ��ǰҳ��
	 */
	private int currentPage = 1;
	/**
	 * �������ŵ�ҵ����
	 */
	private NewsItemBiz mNewsItemBiz;

	/**
	 * �����ݿ⽻��
	 */
	private NewsItemDao mNewsItemDao;

	/**
	 * ��չ��ListView
	 */
	private XListView mXListView;
	/**
	 * ����������
	 */
	private NewsItemAdapter mAdapter;

	/**
	 * ����
	 */
	private List<NewsItem> mDatas = new ArrayList<NewsItem>();

	/**
	 * ���newType
	 * 
	 * @param newsType
	 */
	public MainFragment(int newsType)
	{
		this.newsType = newsType;
		Logger.e(newsType + "newsType");
		mNewsItemBiz = new NewsItemBiz();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.tab_item_fragment_main, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		mNewsItemDao = new NewsItemDao(getActivity());
		mAdapter = new NewsItemAdapter(getActivity(), mDatas);
		/**
		 * ��ʼ��
		 */
		mXListView = (XListView) getView().findViewById(R.id.id_xlistView);
		mXListView.setAdapter(mAdapter);
		mXListView.setPullRefreshEnable(this);
		mXListView.setPullLoadEnable(this);
		mXListView.setRefreshTime(AppUtil.getRefreashTime(getActivity(), newsType));
		// mXListView.NotRefreshAtBegin();

		mXListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				NewsItem newsItem = mDatas.get(position-1);
				Intent intent = new Intent(getActivity(), NewsContentActivity.class);
				intent.putExtra("url", newsItem.getLink());
				startActivity(intent);
			}

		});
		if (isFirstIn)
		{
			/**
			 * ����ʱֱ��ˢ��
			 */
			mXListView.startRefresh();
			isFirstIn = false;
		} else
		{
			mXListView.NotRefreshAtBegin();
		}
	}

	@Override
	public void onRefresh()
	{
		new LoadDatasTask().execute(LOAD_REFREASH);
	}

	@Override
	public void onLoadMore()
	{
		new LoadDatasTask().execute(LOAD_MORE);
	}

	/**
	 * �������ݵ��첽����
	 * 
	 * @author zhy
	 * 
	 */
	class LoadDatasTask extends AsyncTask<Integer, Void, Integer>
	{

		@Override
		protected Integer doInBackground(Integer... params)
		{
			switch (params[0])
			{
			case LOAD_MORE:
				loadMoreData();
				break;
			case LOAD_REFREASH:
				return refreashData();
			}
			return -1;
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			switch (result)
			{
			case TIP_ERROR_NO_NETWORK:
				ToastUtil.toast(getActivity(), "û���������ӣ�");
				mAdapter.setDatas(mDatas);
				mAdapter.notifyDataSetChanged();
				break;
			case TIP_ERROR_SERVER:
				ToastUtil.toast(getActivity(), "����������");
				break;

			default:
				break;

			}

			mXListView.setRefreshTime(AppUtil.getRefreashTime(getActivity(), newsType));
			mXListView.stopRefresh();
			mXListView.stopLoadMore();
		}

	}

	/**
	 * ����ˢ������
	 */
	public Integer refreashData()
	{

		if (NetUtil.checkNet(getActivity()))
		{
			
			isConnNet = true;
			// ��ȡ��������
			try
			{
				List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
				mAdapter.setDatas(newsItems);

				isLoadingDataFromNetWork = true;
				// ����ˢ��ʱ��
				AppUtil.setRefreashTime(getActivity(), newsType);
				// ������ݿ�����
				mNewsItemDao.deleteAll(newsType);
				// �������ݿ�
				mNewsItemDao.add(newsItems);

			} catch (CommonException e)
			{
				e.printStackTrace();
				isLoadingDataFromNetWork = false;
				return TIP_ERROR_SERVER;
			}
		} else
		{Log.e("xxx", "no network");
			isConnNet = false;
			isLoadingDataFromNetWork = false;
			// TODO�����ݿ��м���
			List<NewsItem> newsItems = mNewsItemDao.list(newsType, currentPage);
			mDatas = newsItems;
			// mAdapter.setDatas(newsItems);
			return TIP_ERROR_NO_NETWORK;
		}

		return -1;

	}

	/**
	 * ����ݵ�ǰ����������ж��Ǵ����ݿ���ػ��Ǵ����������ȡ
	 */
	public void loadMoreData()
	{
		// ��ǰ�����Ǵ������ȡ��
		if (isLoadingDataFromNetWork)
		{
			currentPage += 1;
			try
			{
				List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
				mNewsItemDao.add(newsItems);
				mAdapter.addAll(newsItems);
			} catch (CommonException e)
			{
				e.printStackTrace();
			}
		} else
		// �����ݿ���ص�
		{
			currentPage += 1;
			List<NewsItem> newsItems = mNewsItemDao.list(newsType, currentPage);
			mAdapter.addAll(newsItems);
		}

	}

}
