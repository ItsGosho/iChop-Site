package com.ichop.webstorage.components.dropbox;

@FunctionalInterface
interface DropboxActionResolver<T> {

    T perform() throws Exception;

}