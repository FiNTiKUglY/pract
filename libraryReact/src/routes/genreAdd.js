import React, { useState } from 'react';
import { addGenre } from "../api/genreService"
import { useNavigate } from "react-router-dom";

export default function GenresAdd() {
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Название"
                    className="form-control"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <textarea
                    placeholder="Описание"
                    className="form-control"
                    type="text"
                    onChange={(e) => setDescription(e.target.value)}
                />
                <button
                    onClick={addGenreClick}>
                    Добавить
                </button>
            </div>
        </section>
    );

    async function addGenreClick() {
        let genre = {}
        genre.id = crypto.randomUUID();
        genre.name = name
        genre.description = description
        await addGenre(genre)
        navigate('/genres')
    }
}