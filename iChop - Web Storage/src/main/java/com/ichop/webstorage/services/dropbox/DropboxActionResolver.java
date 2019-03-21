package com.ichop.webstorage.services.dropbox;

@FunctionalInterface
interface DropboxActionResolver<T> {

    T perform() throws Exception;

}