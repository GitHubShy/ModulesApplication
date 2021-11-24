package com.modules.basemodule.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.modules.basemodule.http.CommonCallback;
import com.modules.basemodule.http.bean.ResponseRemoteConfig;
import com.modules.basemodule.http.repository.RemoteDataRepository;

import java.util.ArrayList;

/** Viewmodel
 * https://developer.android.com/topic/libraries/architecture/viewmodel
 */
public class RemoteData extends ViewModel {

    private MutableLiveData<ResponseRemoteConfig> remoteConfigs;

    public LiveData<ResponseRemoteConfig> getConfigs() {
        if (remoteConfigs == null) {
            remoteConfigs = new MutableLiveData<ResponseRemoteConfig>();
            loadConfigs();
        }
        return remoteConfigs;
    }

    public void setConfigs(ResponseRemoteConfig configs) {
        remoteConfigs.setValue(configs);
    }

    /**
     * 异步加载服务器json，configs
     */
    private void loadConfigs() {
        RemoteDataRepository.getAliRemoteConfig(new CommonCallback<ArrayList<ResponseRemoteConfig>>() {
            @Override
            public void onResponse(ArrayList<ResponseRemoteConfig> responseRemoteConfigs) {
                if (responseRemoteConfigs != null && responseRemoteConfigs.size() > 0) {
                    remoteConfigs.setValue(responseRemoteConfigs.get(0));
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {

            }
        });
    }
}
