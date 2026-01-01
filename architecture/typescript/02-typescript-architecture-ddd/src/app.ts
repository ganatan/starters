import express from 'express'
import { buildRoutes } from './app/build-routes'

const app = express()
const port = 3000

app.use(express.json())
app.use('/api', buildRoutes())

app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`)
})
