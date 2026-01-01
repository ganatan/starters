import express from 'express'
import { json } from 'express'
import { titleRouter } from './titles/title.router'
import { personRouter } from './persons/person.router'
import { countryRouter } from './countries/country.router'

const app = express()
const port = process.env.PORT ? Number(process.env.PORT) : 3001

app.use(json())

app.use('/titles', titleRouter)
app.use('/persons', personRouter)
app.use('/countries', countryRouter)

app.get('/health', (req, res) => {
  res.json({ status: 'ok' })
})

app.get('/', (req, res) => {
  res.json({
    name: 'backend-typescript',
    version: '1.0.0',
    status: 'ok',
    timestamp: new Date().toISOString()
  })
})

app.listen(port, () => {
  console.log(`Server listening on http://localhost:${port}`)
})
