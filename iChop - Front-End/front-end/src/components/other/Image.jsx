import React, {Component} from 'react';


/*TODO:Да споделя решението си тук:*/
/*https://stackoverflow.com/questions/980855/inputting-a-default-image-in-case-the-src-attribute-of-an-html-img-is-not-vali*/

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

        this.customImageRef = React.createRef();
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = this.state.defaultUrl;
    }

    onLoad() {
        this.setState({avatarUrl: this.state.url})
    }

    render() {
        let {className,style,title} = this.props;

        return (
            <img src={this.state.avatarUrl}
                 onError={this.onUserAvatarError}
                 onLoad={this.onLoad}
                 alt=''
                 title={title}
                 style={style}
                 ref={this.customImageRef}
                 className={className}/>
        );
    }

}

export default Image;