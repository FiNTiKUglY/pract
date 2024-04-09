import React, { useState } from 'react';
import { getGenre, updateGenre } from "../api/genreService"
import { useLoaderData, useNavigate } from "react-router-dom";

export async function loader({ params }) {
    const genre = await getGenre(params.genreId);
    return { genre };
}

export default function GenresUpdate() {
    const navigate = useNavigate();

    const { genre } = useLoaderData();
    const [name, setName] = useState(genre.name);
    const [description, setDescription] = useState(genre.description);

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Название"
                    className="form-control"
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <textarea
                    placeholder="Описание"
                    className="form-control"
                    type="text"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
                <button
                    onClick={updateGenreClick}>
                    Изменить
                </button>
            </div>
        </section>
    );

    async function updateGenreClick() {
        genre.name = name
        genre.description = description
        await updateGenre(genre)
        navigate('/genres')
    }
}
