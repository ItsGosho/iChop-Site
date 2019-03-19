package itsgosho.services;

@FunctionalInterface
interface DropboxActionResolver<T> {

    T perform() throws Exception;

}