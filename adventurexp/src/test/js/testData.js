/*
 * Falske aktiviteter i samme format som JSON fra GET /adventure/activity.
 */
const gocart = {
    name: 'Gocart',
    description: 'Kør om kap.\n\nMed sikkerhedsinstruktion.',
    equipment: 'Hjelm, Balaclava, Kørerdragt',
    imageURL: 'image/Gokart.jpg',
    durationMinutes: 30,
    ageLimit: 14,
    heightLimit: 150
};

const minigolf = {
    name: 'Minigolf',
    description: '18 huller.',
    equipment: 'Ingen',
    imageURL: 'image/minigolf.jpg',
    durationMinutes: 60,
    ageLimit: 0,
    heightLimit: 0
};

module.exports = { gocart, minigolf, activities: [gocart, minigolf] };