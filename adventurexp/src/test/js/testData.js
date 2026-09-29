/*
 * Falske aktiviteter i samme format som JSON fra GET /adventure/activity.
 */
const gocart = {
    name: 'Gocart',
    description: 'Kør om kap.\n\nMed sikkerhedsinstruktion.',
    equipmentTypes: ['Hjelm', 'Balaclava', 'Kørerdragt'],
    imageURL: 'image/Gokart.jpg',
    durationMinutes: 30,
    minAge: 14,
    minHeight: 150
};

const minigolf = {
    name: 'Minigolf',
    description: '18 huller.',
    equipmentTypes: [],
    imageURL: 'image/minigolf.jpg',
    durationMinutes: 60,
    minAge: 0,
    minHeight: 0
};

module.exports = { gocart, minigolf, activities: [gocart, minigolf] };