'use strict'

const { test } = require('node:test')
const assert = require('node:assert')

// Placeholder do golden path: garante o wiring de testes (o gate de CI roda
// `node --test tests/`). Substitua por testes reais das suas rotas.
// TODO(plataforma): extrair app.js (exporta o Fastify) de index.js (listen)
// pra testar rotas com app.inject() sem subir servidor.
test('manifest do serviço é coerente', () => {
  const pkg = require('../package.json')
  assert.ok(pkg.name, 'package.json precisa de name')
  assert.equal(typeof pkg.scripts.start, 'string', 'script start é o contrato do container')
})
