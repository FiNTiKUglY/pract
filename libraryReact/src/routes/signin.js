import React, { useState } from 'react';
import { signIn } from "../api/authService"

export default function Signin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

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
        console.log(data)
    }
}