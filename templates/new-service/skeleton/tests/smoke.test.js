'use strict'

const { test } = require('node:test')
const assert = require('node:assert')

// Placeholder: garante o wiring de testes. Substitua por testes reais.
// TODO(plataforma): extrair app.js de index.js pra testar rotas com app.inject().
test('manifest do serviço é coerente', () => {
  const pkg = require('../package.json')
  assert.ok(pkg.name, 'package.json precisa de name')
  assert.equal(typeof pkg.scripts.start, 'string', 'script start é o contrato do container')
})
