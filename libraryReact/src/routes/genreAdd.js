import React, { useState, useEffect } from 'react';
import { addGenre } from "../api/genreService"
import { useNavigate } from "react-router-dom";

export default function GenresAdd() {
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [genre, setGenre] = useState({});
    const [errors, setErrors] = useState({});
    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        const addData = async (genre) => {
            await addGenre(genre);
        }
        if (Object.keys(errors).length === 0 && submitting) {
            addData(genre);
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
        setErrors(validateValues(genre))
        setGenre(genre)
        setSubmitting(true);
    }
}