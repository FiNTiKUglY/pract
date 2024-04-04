import React, { useState } from 'react';
import { signIn } from "../api/authService"
import { useCookies } from 'react-cookie'

export default function Signin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [cookies, setCookie, removeCookie] = useCookies(['jwt_token', 'user']);

    return (
        <section>
            <input
                placeholder="Email"
                type="email"
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                placeholder="Пароль"
                type="password"
                onChange={(e) => setPassword(e.target.value)}
            />
            <button
                onClick={signInClick}>
                Войти
            </button>
        </section>
    );

    async function signInClick() {
        let data = await signIn(email, password)
        setCookie('jwt_token', data.token,
        {
            maxAge: 3600
        });
        setCookie('user', data.id,
        {
            maxAge: 3600
        });
        console.log(data)
    }

    async function logOutClick() {
        removeCookie('jwt_token');
        removeCookie('user');
    }
}