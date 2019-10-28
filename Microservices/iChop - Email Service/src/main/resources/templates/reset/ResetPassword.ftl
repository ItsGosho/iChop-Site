<#-- @ftlvariable name="emailResetPasswordView" type="ichop.emails.domain.models.templates.EmailResetPasswordView" -->


<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
        @media screen {
            @font-face {
                font-family: 'Source Sans Pro';
                font-style: normal;
                font-weight: 400;
                src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff');
            }
            @font-face {
                font-family: 'Source Sans Pro';
                font-style: normal;
                font-weight: 700;
                src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff');
            }
        }

        body,
        table,
        td,
        a {
            -ms-text-size-adjust: 100%; /* 1 */
            -webkit-text-size-adjust: 100%; /* 2 */
        }

        table,
        td {
            mso-table-rspace: 0pt;
            mso-table-lspace: 0pt;
        }

        img {
            -ms-interpolation-mode: bicubic;
        }

        a[x-apple-data-detectors] {
            font-family: inherit !important;
            font-size: inherit !important;
            font-weight: inherit !important;
            line-height: inherit !important;
            color: inherit !important;
            text-decoration: none !important;
        }

        div[style*="margin: 16px 0;"] {
            margin: 0 !important;
        }

        body {
            width: 100% !important;
            height: 100% !important;
            padding: 0 !important;
            margin: 0 !important;
        }

        table {
            border-collapse: collapse !important;
        }

        a {
            color: #1a82e2;
        }

        img {
            height: auto;
            line-height: 100%;
            text-decoration: none;
            border: 0;
            outline: none;
        }
    </style>

</head>
<body style="background-color: #e9ecef;">

<table border="0" cellpadding="0" cellspacing="0" width="100%">

    <tr>
        <td align="center" bgcolor="#e9ecef">
            <table align="center" border="0" cellpadding="0" cellspacing="0" width="600">

                <tr>
                    <td align="center" bgcolor="#e9ecef">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="600">
                            <tr>
                                <td align="center" valign="top" width="600">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">
                            <tr>
                                <td align="left" bgcolor="#ffffff"
                                    style="padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;">
                                    <h1 style="margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;">
                                        Reset Your Password</h1>
                                </td>
                            </tr>
                        </table>
                        </td>
                        </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td align="center" bgcolor="#e9ecef">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="600">
                            <tr>
                                <td align="center" valign="top" width="600">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">

                            <tr>
                                <td align="left" bgcolor="#ffffff"
                                    style="padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;">
                                    <p style="margin: 0;">We have received request that you want to change your password :)</p>
                                    <p style="margin: 0;">Your token will expire on ${emailResetPasswordView.expirationDate} or when you request a new one.</p>
                                </td>
                            </tr>
                            <!-- end copy -->

                            <!-- start button -->
                            <tr>
                                <td align="left" bgcolor="#ffffff">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td align="center" bgcolor="#ffffff" style="padding: 12px;">
                                                <table border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td align="center" bgcolor="#1a82e2"
                                                            style="border-radius: 6px;">
                                                            <a href="${emailResetPasswordView.passwordResetUrl}" target="_blank"
                                                               style="display: inline-block; padding: 16px 36px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; border-radius: 6px;">Do
                                                                Reset</a>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td align="left" bgcolor="#ffffff"
                                    style="padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;">
                                    <p style="margin: 0;">If that doesn't work, copy and paste the following link in
                                        your browser:</p>
                                    <p style="margin: 0;"><a href="${emailResetPasswordView.passwordResetUrl}" target="_blank">${emailResetPasswordView.passwordResetUrl}</a>
                                    </p>
                                </td>
                            </tr>

                            <tr>
                                <td align="left" bgcolor="#ffffff"
                                    style="padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; border-bottom: 3px solid #d4dadf">
                                    <p style="margin: 0;">iChop,<br> See you again!</p>
                                </td>
                            </tr>
                        </table>
                        </td>
                        </tr>
                        </table>
                    </td>
                </tr>

            </table>
</body>
</html>