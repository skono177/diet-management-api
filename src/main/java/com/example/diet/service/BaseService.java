package com.example.diet.service;

public interface BaseService<T, V> {

    /**
     * バリデーションチェック
     * 
     * @param value リクエスト値
     * @return チェック結果 true：エラーなし false：エラーあり
     */
    Boolean validation(T value);

    /**
     * Service処理
     * 
     * @param userId ユーザID
     * @param value リクエスト値
     * @return 処理結果
     */
    V execute(String userId, T value) throws Exception;

}