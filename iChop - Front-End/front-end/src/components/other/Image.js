import React, {Component} from 'react';

class Image extends Component {

    constructor(props) {
        super(props);

        let {url, defaultUrl} = this.props;

        this.state = {
            url: url,
            defaultUrl: defaultUrl,

            avatarUrl: defaultUrl
        };

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
        this.onLoad = this.onLoad.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = this.state.defaultUrl;
    }

    onLoad() {
        this.setState({avatarUrl: this.state.url})
    }

    render() {
        let {className} = this.props;

        return (
            <img
                src={this.state.avatarUrl}
                onError={this.onUserAvatarError}
                onLoad={this.onLoad}
                alt=''
                className={className}/>
        );
    }

}

export default Image;