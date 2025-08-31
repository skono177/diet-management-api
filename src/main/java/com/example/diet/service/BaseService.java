package com.example.diet.service;

public interface BaseService<T, S, V> {

    /**
     * バリデーションチェック
     * 
     * @param value リクエスト値
     * @return チェック結果 true：エラーなし false：エラーあり
     */
    Boolean validation(T value);

    /**
     * 処理用パラメータ作成
     * 
     * @param value リクエスト値
     * @return 処理用パラメータ
     */
    S createParam(T value);

    /**
     * Service処理
     * 
     * @param userId ユーザID
     * @param param  処理用パラメータ
     * @return 処理結果
     */
    V execute(String userId, S param);

}