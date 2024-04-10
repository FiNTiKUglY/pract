import React, { useState, useEffect } from 'react';
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
    const [newGenre, setNewGenre] = useState({});
    const [errors, setErrors] = useState({});
    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        const updateData = async (genre) => {
            await updateGenre(genre);
        }
        if (Object.keys(errors).length === 0 && submitting) {
            updateData(newGenre);
            navigate('/genres');
        }
      }, [errors]);

      const validateValues = (genre) => {
        let errors = {};
        if (genre.name.length < 1) {
            errors.name = "Название слишком короткое";
            alert("Название слишком короткое")
        }
        if (genre.name.length > 255) {
            errors.title = "Название слишком длинное";
            alert("Название слишком длинное")
        }
        if (genre.description.length < 5) {
            errors.shortDescription = "Описание слишком короткое";
            alert("Описание слишком короткое")
        }
        if (genre.description.length > 255) {
            errors.shortDescription = "Описание слишком длинное";
            alert("Описание слишком длинное")
        }
        return errors;
    };

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
        setErrors(validateValues(genre))
        setNewGenre(genre)
        setSubmitting(true);
    }
}
