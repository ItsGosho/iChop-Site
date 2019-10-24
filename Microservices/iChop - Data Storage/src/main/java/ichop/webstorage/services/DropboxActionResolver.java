package ichop.webstorage.services;

@FunctionalInterface
interface DropboxActionResolver<T> {

    T perform() throws Exception;

}